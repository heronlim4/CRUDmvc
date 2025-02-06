// Função para listar os itens
function listarItens() {
    fetch("/api/itens/listar", {
        method: "GET"
    })
    .then(response => response.json())
    .then(data => {
        let itensDiv = document.getElementById("itens");
        itensDiv.innerHTML = "<ul>";
        data.forEach(item => {
            itensDiv.innerHTML += `
                <li>
                    ${item.nome}
                    <button onclick="editarItem('${item.nome}')">Editar</button>
                    <button onclick="removerItem('${item.nome}')">Excluir</button>
                </li>`;
        });
        itensDiv.innerHTML += "</ul>";
    })
    .catch(error => console.log(error));
}

// Função para cadastrar um item
function cadastrarItem() {
    const nome = prompt("Digite o nome do item:");
    if (nome && nome.trim() !== "") {
        const item = { nome: nome };
        fetch("/api/itens/cadastrar", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(item)
        })
        .then(response => response.json())
        .then(() => listarItens())
        .catch(error => console.log(error));
    } else {
        alert("O nome do item não pode ser vazio.");
    }
}

// Função para editar um item
function editarItem(nome) {
    const novoNome = prompt("Digite o novo nome para o item:", nome);
    if (novoNome && novoNome.trim() !== "") {
        const item = { nome: novoNome };
        fetch(`/api/itens/editar/${nome}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(item)
        })
        .then(response => response.json())
        .then(() => listarItens())
        .catch(error => console.log(error));
    } else {
        alert("O nome do item não pode ser vazio.");
    }
}

// Função para remover um item
function removerItem(nome) {
    fetch(`/api/itens/remover/${nome}`, {
        method: "DELETE"
    })
    .then(() => listarItens())
    .catch(error => console.log(error));
}
