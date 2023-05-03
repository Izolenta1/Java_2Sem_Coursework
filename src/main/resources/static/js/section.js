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

function fillPage(searchRes) {
    var blockField = document.querySelector(".blocksWrapper")
    var sectionName = searchRes.split("=")[1]

    var header = document.querySelector(".main_text")
    header.innerHTML = sectionName

    let xhrGetGoods = new XMLHttpRequest()
    xhrGetGoods.open("GET", `${document.location.protocol}//${document.location.hostname}:${document.location.port}/toy?section=${sectionName}`, false)
    xhrGetGoods.send()

    let response = JSON.parse(xhrGetGoods.responseText)

    if (response.length == 0) {
        blockField.innerHTML = "Товар отсутствует"
        return
    }

    for(let good of response) {
        var block = document.createElement("div")
        block.classList.add("main_block")
        block.innerHTML = `<a href="goodCard?id=${good["id"]}"><img src="img/goodsImg/${good["id"]}.png" class="main_blockImg"></a>
                            <a href="goodCard?id=${good["id"]}">${good["name"]}</a>
                            <a href="goodCard?id=${good["id"]}">${good["cost"]} ₽</a>`
        var cartBTN = document.createElement("div")
        cartBTN.innerHTML = "В КОРЗИНУ"
        cartBTN.addEventListener("click", function() {addToCart(good["id"])})
        block.append(cartBTN)

        blockField.append(block)
    }
}

function onLoad() {
    fillPage(decodeURI(location.search))
}

window.addEventListener("load", onLoad);