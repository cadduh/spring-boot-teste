function deletar(){
    var objeto = {
		"id" : document.getElementById("txtId").value,
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

    fetch("http://localhost:8080/deletarprodutos", cabecalho)
        .then(res => res.json())
        .then(res => {
            localStorage.setItem("logado",JSON.stringify(res));
            document.querySelector("#modal").classList.toggle("hide");
			document.querySelector('input#txtId').value= ""
			document.querySelector('input#txtNome').value= ""
		    document.querySelector('input#txtCod').value= ""
			document.querySelector('input#txtQnt').value= ""
		    document.querySelector('input#txtValor').value= ""
            window.alert("Excluído com sucesso! ");
        })
        .catch(err => {
            window.alert("Deu ruim");
        });
    

}