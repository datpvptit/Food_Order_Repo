// Thêm chi tiết các món ăn chọn vào local Storage
const arr = new Set();
const btn = document.querySelectorAll(".addBtn");
var cartSum = 0;
btn.forEach(function (button, index) {
  button.addEventListener("click", function (event) {
    {
      var bntItem = event.target;
      var product =
        bntItem.parentElement.parentElement.parentElement.parentElement;
      console.log(
        product
          .querySelector(".menu-img.img")
          .style.backgroundImage.match(/"([^"]+)"/)[1]
      );
      var productImg = product
        .querySelector(".menu-img.img")
        .style.backgroundImage.match(/"([^"]+)"/)[1];

      var productPrice = parseInt(
        product.querySelector(".price").innerText.match(/\d+/)[0]
      );
      var productName = product.querySelector("h3").innerText;
      var productId = product.querySelector(
        ".text.d-flex.align-items-center"
      ).id;

      // console.log(product.querySelector(".text.d-flex.align-items-center").id);
      var test = localStorage.getItem(productId);
      if (test) {
        alert("Món ăn của bạn đã có trong giỏ hàng");
      } else {
        localStorage.setItem(
          productId,
          JSON.stringify({
            id: productId,
            name: productName,
            img: productImg,
            price: productPrice,
            quantity: 1,
          })
        );
        var append1 = document.createElement("script");

        append1.src = "js/append.js";

        append1.onload = function () {
          addCart();
        };
        document.head.appendChild(append1);
      }
    }
  });
});


const orderBtns = document.querySelectorAll(".orderBtn");
orderBtns.forEach(function (orderBtn) {
  orderBtn.addEventListener("click", function () {
    var orderBill = [];
    for (var i = 0; i < localStorage.length; i++) {
      var key = localStorage.key(i);
      var orderItem = JSON.parse(localStorage.getItem(key));
      orderBill.push(({ foodID: orderItem.id, number: orderItem.quantity }));
    }
    const data = { userId: sessionStorage.getItem("id"), orderItemDTOList: orderBill };
    console.log(data);
    if (orderBill.length > 0) {
      $.ajax({
        method: "POST",
        url: "http://localhost:8080/api/order/add",
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer " + sessionStorage.getItem("token")
        },
        data: JSON.stringify(data),
        success: function (response) {
          if (response.success) {
            sessionStorage.setItem("payStatus", response.data.payStatus);
            location.reload();

          } else {
            console.error("Thêm order thất bại:", response.error);
          }
        },
        error: function (xhr, status, error) {
          console.error("Lỗi khi gọi API:", error);
        }
      });
    }
  });
});

$(document).ready(function() {
  var payStatus = sessionStorage.getItem("payStatus");
  if (payStatus == "false") {
      $('.orderBtn').hide();
      var payBtn = `<a href="thanhtoan.html"class="btn btn-primary payBtn">Thanh toán</a>` ;
      $('.price-total').append(payBtn);
  }
});