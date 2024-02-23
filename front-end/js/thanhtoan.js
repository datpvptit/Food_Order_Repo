document.addEventListener("DOMContentLoaded", function () {
    let size = 0;
    let tong = 0;
    let ul = document.querySelector(".list-group");
    for (var j = 0; j < localStorage.length; j++) {
        var key = localStorage.key(j);
        console.log(key)
        if(isNaN(key)){
          continue;
        }
        let menu = JSON.parse(localStorage.getItem(key));
        let li = document.createElement("li");        
        li.classList.add(
            "list-group-item",
            "d-flex",
            "justify-content-between",
            "lh-condensed"
          );       
        li.innerHTML = `        
            <div>
                <h6 class="my-0">${menu.name}</h6>
                <small class="text-muted">${menu.price} x ${menu.quantity}</small>
            </div>
            <span class="text-muted">${menu.price * menu.quantity}</span>        
        `       
        ul.appendChild(li);
        tong += menu.price * menu.quantity;
        size += 1;
    }
    var user = sessionStorage.getItem("user");
    if (user) {
      var username = user.split('@')[0];
      $("#kh_ban").text(`Bàn: ${username}`);
    } else {
        console.error("Không tìm thấy giá trị user trong sessionStorage");
    }
    
    document.querySelector(".badge.badge-secondary.badge-pill").textContent = size;
    let li = document.createElement("li");        
    li.classList.add(
        "list-group-item",
        "d-flex",
        "justify-content-between",        
      ); 
      li.innerHTML = `        
        <span>Tổng thành tiền</span>
        <strong>${tong}</strong>      
    ` 
    ul.appendChild(li);
})
