function fillCard(searchRes) {
    var goodId = searchRes.split("=")[1]

    let xhrGetGood = new XMLHttpRequest()
    xhrGetGood.open("GET", `${document.location.protocol}//${document.location.hostname}:${document.location.port}/toy?id=${goodId}`, false)
    xhrGetGood.send()

    let response = JSON.parse(xhrGetGood.responseText)[0]

    var header = document.getElementById("header")
    var picture = document.getElementById("picture")
    var cost = document.getElementById("cost")
    var onStore = document.getElementById("onStore")
    var description = document.getElementById("description")
    var country = document.getElementById("country")
    var age = document.getElementById("age")
    var cartBTN = document.getElementById("cartBTN")

    header.innerHTML = response["name"]
    picture.innerHTML = `<img src="img/goodsImg/${response["id"]}.png" class="goodImg goodImgAdaptation">`
    cost.innerHTML = response["cost"] + " руб."
    onStore.innerHTML = response["on_store"] > 0 ? `Товар есть в наличии (${response["on_store"]})` : "Товара нет в наличии"
    description.innerHTML = response["description"]
    country.innerHTML = "Страна изготовитель: " + response["country"]
    age.innerHTML = "Рекомендуемый возраст: " + response["age"] + " лет"
    cartBTN.addEventListener("click", function() {addToCart(Number(response["id"]))})
}

function addToCart(id) {
    if (localStorage.getItem('cart') != null) {
        var savedMas = JSON.parse(localStorage.getItem("cart"))
        savedMas.push(id)
        localStorage.setItem("cart", JSON.stringify(savedMas))
    }
    else {
        var mas = []
        mas.push(id)
        localStorage.setItem("cart", JSON.stringify(mas))
    }
    updateCartIcon()
}

function onLoad() {
    fillCard(location.search)
}

window.addEventListener("load", onLoad);