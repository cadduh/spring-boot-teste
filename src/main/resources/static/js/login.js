function logar(){
    var objeto = {
        "nome" : document.getElementById("txtNome").value,
        "codigo" : document.getElementById("txtCod").value
    }

    var cabecalho = {
        method: "POST",
        body: JSON.stringify(objeto),
        headers:{
            "Content-type":"application/json"
        }
    }

    fetch("http://localhost:8080/produtos/login", cabecalho)
        .then(res => res.json())
        .then(res => {
            localStorage.setItem("logado",JSON.stringify(res));
            document.querySelector("#modal").classList.toggle("hide");
			document.querySelector('input#txtQnt').defaultValue= res.quantidade
		    document.querySelector('input#txtValor').defaultValue= res.valor
            window.alert("Login Realizado com sucesso! ");
        })
        .catch(err => {
            window.alert("Deu ruim");
        });
    

}