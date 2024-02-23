var cartSum = 0;
var count = 0;
//-----------Hiển thị--------------------
function addCart() {
  cartSum = 0;
  var data = ``;
  var menu1 = [];
  var cartitem = document.querySelectorAll("tbody tr");
  for (var i = 0; i < cartitem.length; i++) {
    for (var j = 0; j < localStorage.length; j++) {
      var key = localStorage.key(j);
      if (key == cartitem[i].querySelector("tr td").id) {
        var save = JSON.parse(localStorage.getItem(key));
        localStorage.removeItem(key);
        save.quantity = cartitem[i].querySelector("input").value;
        localStorage.setItem(key, JSON.stringify(save));
      }
    }
  }

  for (var j = 0; j < localStorage.length; j++) {
    var key = localStorage.key(j);
    var menu = JSON.parse(localStorage.getItem(key));
    var trcontent =
      '<tr><td id="' +
      menu.id +
      '"><img style="width: 70px" src="' +
      menu.img +
      '" alt="" /></td><td class = "name" stype="display : flex;align-items: center;">' +
      menu.name +
      "</td><td><p><span>" +
      menu.price +
      '</span></p></td><td><input class="nhap" style="width: 60px; outline: none"      type="number"      value="' +
      menu.quantity +
      '"      min="1"    />  </td>  <td style="cursor: pointer"><span class="cart-delete">Xóa</span></td></tr>';
    data += trcontent;
    cartSum += menu.price * menu.quantity;
  }
  document.getElementById("themhtml").innerHTML = data;

  cartotal(0);
}


//-----------Tính tổng--------------
function cartotal(productPrice) {
  var addSum = document.querySelector(".price-total span");
  var addSumCart = document.querySelector(".cart-icon span");
  cartSum = cartSum + productPrice;
  addSumCart.innerHTML = `${cartSum} $`;
  addSum.innerHTML = cartSum;
  inputChange();
  deleteCart();
}

//---------------Hàm xóa-----------------
function deleteCart() {
  var lostMoney = 0;
  var cartitem = document.querySelectorAll("tbody tr");
  var productDelete = document.querySelectorAll(".cart-delete");
  for (var i = 0; i < cartitem.length; i++) {
    productDelete[i].addEventListener("click", function (event) {
      var cartDelete = event.target;
      var cart1 = cartDelete.parentElement.parentElement;
      lostMoney =
        JSON.parse(localStorage.getItem(cart1.querySelector("td").id)).price *
        JSON.parse(localStorage.getItem(cart1.querySelector("td").id)).quantity;
      //   arr.delete(cart1.querySelector(".name").innerText);
      localStorage.removeItem(cart1.querySelector("td").id);
      cart1.remove();
      cartotal(-lostMoney);
    });
  }
}

//-------------------Hàm thay đổi số lượng ---------------------------
function inputChange() {
  var cartitem = document.querySelectorAll("tbody tr");
  for (var i = 0; i < cartitem.length; i++) {
    var iteamChange = cartitem[i].querySelector("input");
    iteamChange.addEventListener("change", function () {
      addCart();
    });
  }
}
addCart();

const cartbtn = document.querySelector(".fa-solid.fa-x");
const cartshow = document.querySelector(".fa-solid.fa-cart-shopping");
cartshow.addEventListener("click", function () {
  document.querySelector(".cart").style.right = "0";
});

cartbtn.addEventListener("click", function () {
  document.querySelector(".cart").style.right = "-100%";
});
