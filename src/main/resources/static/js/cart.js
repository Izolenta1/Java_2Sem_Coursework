function fillCart() {
    var cartField = document.querySelector(".cartField")
    var totalCost = document.getElementById("totalCost")

    var savedMas = JSON.parse(localStorage.getItem("cart"))
    var newTotalCost = 0;
    if (savedMas != null) {
        for (let i = 0; i < savedMas.length; i++) {
            let xhrGetGoods = new XMLHttpRequest()
            xhrGetGoods.open("GET", `${document.location.protocol}//${document.location.hostname}:${document.location.port}/toy?id=${savedMas[i]}`, false)
            xhrGetGoods.send()

            let response = JSON.parse(xhrGetGoods.responseText)[0]

            newTotalCost += response["cost"]

            let block = document.createElement("div")
            block.innerHTML += `<img src="img/goodsImg/${response["id"]}.png">`
            var innerBlock = document.createElement("div")
            innerBlock.innerHTML += `<span>${response["name"]}</span>`
            var delBTN = document.createElement("div")
            delBTN.innerHTML = "Удалить из корзины"
            delBTN.classList.add("testclass")
            delBTN.addEventListener("click", function() {deleteFromCart(block, response["id"])})
            innerBlock.append(delBTN)
            block.append(innerBlock)
            var spanElem = document.createElement("span")
            spanElem.innerHTML = `<span>${response["cost"]} ₽</span>`
            block.append(spanElem)
            cartField.append(block)
        }
    }
    totalCost.innerHTML = `Всего к оплате: ${newTotalCost} ₽`
}

function updateTotalCost() {
    var totalCost = document.getElementById("totalCost")

    var savedMas = JSON.parse(localStorage.getItem("cart"))
    var newTotalCost = 0;
    if (savedMas != null) {
        for (let i = 0; i < savedMas.length; i++) {
            let xhrGetGoods = new XMLHttpRequest()
            xhrGetGoods.open("GET", `${document.location.protocol}//${document.location.hostname}:${document.location.port}/toy?id=${savedMas[i]}`, false)
            xhrGetGoods.send()

            let response = JSON.parse(xhrGetGoods.responseText)[0]

            newTotalCost += response["cost"]
        }
    }
    totalCost.innerHTML = `Всего к оплате: ${newTotalCost} ₽`
}


function deleteFromCart(elem, id) {
    elem.remove()

    var savedMas = JSON.parse(localStorage.getItem("cart"))
    var index = savedMas.indexOf(id)
    savedMas.splice(index, 1)
    localStorage.setItem("cart", JSON.stringify(savedMas))
    updateCartIcon()
    updateTotalCost()
}

function initializePurchaseBTN() {
    let purchaseBTN = document.querySelector("#purchaseBTN")
    let purchaseField = document.querySelector("#purchaseField")
    purchaseBTN.addEventListener("click", function() {
        purchaseField.setAttribute("style", "display: flex")
    })

    let closePurchaseFieldBTN = document.querySelector("#closePurchaseFieldBTN")
    closePurchaseFieldBTN.addEventListener("click", function() {
        purchaseField.setAttribute("style", "display: none")
    })

    let orderVerificationBTN = document.querySelector("#orderVerificationBTN")
    orderVerificationBTN.addEventListener("click", function() {
        let savedMas = JSON.parse(localStorage.getItem("cart"))
        if (savedMas == null) {
            return
        }

        purchaseField.setAttribute("style", "display: none")

        let purchName = document.querySelector("#purchName")
        let purchLast = document.querySelector("#purchLast")
        let purchEmail = document.querySelector("#purchEmail")
        let purchNumber = document.querySelector("#purchNumber")
        let purchAddress = document.querySelector("#purchAddress")
        let purchComment = document.querySelector("#purchComment")

        let xhrOrder = new XMLHttpRequest()
        xhrOrder.open("POST", `${document.location.protocol}//${document.location.hostname}:${document.location.port}/order`, false)
        xhrOrder.setRequestHeader("Content-Type", "application/json")

        let orderInfo = {
            "name": purchName.value,
            "last": purchLast.value,
            "number": purchNumber.value,
            "address": purchAddress.value,
            "comment": purchComment.value,
            "email_to": purchEmail.value,
            "order_goods": savedMas
        }

        xhrOrder.send(JSON.stringify(orderInfo))

        if (xhrOrder.responseText == "Success") {
            localStorage.removeItem("cart")
            location.reload();
        }

        let notificationWindow = document.querySelector(".notificationWindow")
        if (xhrOrder.responseText == "Error") {
            notificationWindow.setAttribute("style", "display: flex")
        }
    })
}

function onLoad() {


    fillCart()
    initializePurchaseBTN()
}

window.addEventListener("load", onLoad);