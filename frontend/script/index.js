console.log("---- JS - OK");

const URLParams = new URLSearchParams(window.location.search);
const filtro = URLParams.get("filtro");



if (filtro == null)
    elencoPizze();
else {

    axios.get(`http://localhost:8080/pizze?filtro=${filtro}`).then((result) => {
        console.log("test axios.get OK", result);
        document.querySelector("#table_elenco_pizze").innerHTML = '';
        result.data.forEach(pizza => {
            console.log("vedi pizza: ", pizza);
            document.querySelector("#table_elenco_pizze").innerHTML +=
                `
            <tr>
                <td>
                    <a href="./show.html?id=${pizza.id}">${pizza.id}</a>
                </td>
                <td>${pizza.nome}</td>
                <td>${pizza.descrizione}</td>
                <td>${pizza.prezzo} €</td>
            </tr>
            `
        });
    }).catch((result) => {
        console.log("Errore row 11 show.js", result);
        alert("Errore");
    })

}

function elencoPizze() {
    axios.get('http://localhost:8080/pizze').then((result) => {
        console.log("test axios.get OK", result);
        document.querySelector("#table_elenco_pizze").innerHTML = '';
        result.data.forEach(pizza => {
            console.log("vedi pizza: ", pizza);
            document.querySelector("#table_elenco_pizze").innerHTML +=
                `
            <tr>
                <td>
                    <a href="./show.html?id=${pizza.id}">${pizza.id}</a>
                </td>
                <td>${pizza.nome}</td>
                <td>${pizza.descrizione}</td>
                <td>${pizza.prezzo} €</td>
            </tr>
            `
        });
    }).catch((result) => {
        console.log("test axios.get ERROR", result);
        alert("Errore: row 23 index.js!");
    })
}