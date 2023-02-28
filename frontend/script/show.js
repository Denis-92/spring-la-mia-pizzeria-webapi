const URLParams = new URLSearchParams(window.location.search);
const pizzaId = URLParams.get("id");

axios.get(`http://localhost:8080/pizze/${pizzaId}`).then((result) => {
    console.log("test axios.get OK", result);
    document.querySelector("#id").innerHTML = result.data.id;
    document.querySelector("#nome").innerHTML = result.data.nome;
    document.querySelector("#descrizione").innerHTML = result.data.descrizione;
    document.querySelector("#prezzo").innerHTML = result.data.prezzo;
}).catch((result) => {
    console.log("Errore row 11 show.js", result);
    alert("Errore");
})