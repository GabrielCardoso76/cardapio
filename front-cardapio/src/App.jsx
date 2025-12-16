import { useState, useEffect } from 'react';

function App() {
  // 1. Estado para armazenar a lista de produtos
  const [produtos, setProdutos] = useState([]);
  
  // 2. Estados para controlar os inputs do formulário (Componentes Controlados)
  const [nome, setNome] = useState('');
  // Usamos null inicialmente ou convertemos para número na hora de enviar, pois o input é "number"
  const [preco, setPreco] = useState(0); 
  const [quantidade, setQuantidade] = useState(0);
  const [observacao, setObservacao] = useState('');


  // Função para buscar e carregar a lista de produtos (roda apenas uma vez)
  useEffect(() => {
    const fetchProdutos = async () => {
      try {
        // Buscando todos os produtos no endpoint GET
        const response = await fetch('http://localhost:8080/produto'); 
        const data = await response.json();
        setProdutos(data);
        console.log("Produtos carregados:", data);
      } catch (error) {
        console.error("Erro ao buscar produtos:", error);
      }
    };

    fetchProdutos();
  }, []); // Array de dependências vazio: roda apenas na montagem


  // Função para salvar um novo produto no back-end
  const handleSaveProduto = async (e) => {
    // 🛑 1. Previne o comportamento padrão do formulário (que é recarregar a página)
    e.preventDefault();

    // Cria o objeto com os dados do estado
    const novoProduto = {
      nome,
      // 💡 Converte para número, pois o valor do input vem como string, 
      // e seu backend espera Double/int
      preco: Number(preco), 
      quantidade: Number(quantidade),
      observacao,
    };

    try {
      // 🌐 2. Faz a requisição POST para o endpoint /save
      const response = await fetch('http://localhost:8080/produto/save', { // Endpoint POST /produto/save
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(novoProduto), // Envia o objeto como JSON
      });

      if (response.ok) {
        const produtoSalvo = await response.json();
        
        // ✅ 3. Atualiza a lista de produtos no frontend com o novo item
        // O spread operator (...) cria uma nova lista incluindo os produtos antigos e o novo.
        setProdutos([...produtos, produtoSalvo]);

        // 🧹 4. Limpa o formulário após o sucesso
        setNome('');
        setPreco(0);
        setQuantidade(0);
        setObservacao('');
        console.log("Produto salvo com sucesso!");
      } else {
        console.error("Falha ao salvar produto:", response.statusText);
      }

    } catch (error) {
      console.error("Erro na comunicação com a API:", error);
    }
  };


  return (
    <div style={{ padding: '20px' }}>
      <h1>Cardápio de Produtos</h1>

      {/* 💾 Formulário conectado à função de salvar */}
      <form onSubmit={handleSaveProduto}> {/* 👈 Chama a função ao submeter */}
        <div style={{ marginBottom: '10px' }}>
          <label htmlFor="nome">Nome:</label>
          <input type="text" id="nome" value={nome} onChange={(e) => setNome(e.target.value)} required />
        </div>
        
        <div style={{ marginBottom: '10px' }}>
          <label htmlFor="preco">Preço:</label>
          <input type="number" id="preco" value={preco} onChange={(e) => setPreco(e.target.value)} min="0" step="0.01" required/>
        </div>

        <div style={{ marginBottom: '10px' }}>
          <label htmlFor="quantidade">Quantidade:</label>
          <input type="number" id="quantidade" value={quantidade} onChange={(e) => setQuantidade(e.target.value)} min="0" required/>
        </div>

        <div style={{ marginBottom: '20px' }}>
          <label htmlFor="observacao">Observação:</label>
          <input type="text" id="observacao" value={observacao} onChange={(e) => setObservacao(e.target.value)}/>
        </div>

        <button type="submit">Salvar Produto</button>
      </form>

      <hr style={{ margin: '30px 0' }} />

      {/* 🧭 Lista de Produtos */}
      <h2>Produtos Atuais</h2>
      <ul>
        {produtos.map((produto) => (
          <li key={produto.id}>
            <strong>{produto.nome}</strong> - R$ {produto.preco} - Qtd: {produto.quantidade}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;