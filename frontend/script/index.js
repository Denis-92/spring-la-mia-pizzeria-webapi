console.log("---- JS - OK");

elencoPizze();

function elencoPizze() {
    axios.get('http://localhost:8080/pizze').then((result) => {
        console.log("test axios.get OK", result);
        document.querySelector("#table_elenco_pizze").innerHTML = '';
        result.data.forEach(pizza => {
            console.log("vedi pizza: ", pizza);
            document.querySelector("#table_elenco_pizze").innerHTML +=
                `
            <tr>
                <td>${pizza.id}</td>
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