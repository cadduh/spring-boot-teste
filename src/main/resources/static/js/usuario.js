function logar(){
    var objeto = {
        "nome" : document.getElementById("txtNome").value,
        "codigo" : document.getElementById("txtCod").value,
		"quantidade" : document.getElementById("txtQnt").value,
		"valor" : document.getElementById("txtValor").value
    }

    var cabecalho = {
        method: "POST",
        body: JSON.stringify(objeto),
        headers:{
            "Content-type":"application/json"
        }
    }

    fetch("http://localhost:8080/novoprodutos", cabecalho)
        .then(res => res.json())
        .then(res => {
            localStorage.setItem("logado",JSON.stringify(res));
            window.alert("CADASTRO Realizado com sucesso! ");
        })
        .catch(err => {
            window.alert("Deu ruim");
        });
    

}