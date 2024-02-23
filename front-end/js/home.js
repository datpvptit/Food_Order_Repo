document.addEventListener("DOMContentLoaded", function () {
    // Gọi API để lấy danh sách các category
    fetch("http://localhost:3000/category")
  .then((response) => response.json())
  .then((data) => {
    // Lặp qua từng category và hiển thị vào HTML
    let row = document.getElementById("menuContent");
    let cnt = 0;
    let isMaxReached = false; // Biến để kiểm tra điều kiện cnt === 6
    data.data.forEach((category, index) => {
      // Lặp qua từng món ăn trong category và hiển thị vào danh sách món ăn
      category.foodDTOList.forEach((food, index) => {
        if (isMaxReached) return; // Nếu điều kiện cnt === 6 đã đạt được, thoát khỏi vòng lặp
        cnt += 1;
        let col = document.createElement("div");
        col.classList.add(
          "col-md-12",
          "col-lg-6",
          "d-flex",
          "align-self-stretch"
        );
        let foodItem = document.createElement("div");
        foodItem.classList.add(
          "menus",
          "d-sm-flex",
          "ftco-animate",
          "align-items-stretch",
          "fadeInUp",
          "ftco-animated"
        );

        // Kiểm tra xem index có phải là số lẻ hay không
        let isOdd = index % 2 !== 0;

        // Tạo class cho menu-img tùy thuộc vào index là chẵn hay lẻ
        let menuImgClass = isOdd
          ? "menu-img img order-md-last"
          : "menu-img img";
        foodItem.innerHTML = `
                  <div class="${menuImgClass}" style="background-image: url(images/${
          food.image
        });"></div>
                  <div class="text d-flex align-items-center" id=${index + 1}>
                                              <div>
                                                  <div class="d-flex">
                                                      <div class="one-half">
                                                          <h3>${food.title}</h3>
                          
                                                      </div>
                                                      <div class="one-forth">
                                                          <span class="price">$${food.price}</span>
                                                      </div>
                                                  </div>
                                                  <p><span>Thịt</span>, <span>Khoai tây</span>, <span>Gạo</span>,
                                                      <span>Cà chua</span>
                                                  </p>
                                                  <p><button class="btn btn-primary addBtn">Thêm món</button></p>
                                              </div>
                                          </div>
                  
              `;
        col.appendChild(foodItem);
        row.appendChild(col);
        if (cnt === 6) {
          isMaxReached = true; // Đánh dấu điều kiện đã đạt được
          return; // Thoát khỏi vòng lặp khi cnt === 6
        }
      });

      // Thêm tab-pane vào tab-content

      // Kiểm tra xem các script đã được thêm vào trang chưa
      const appendScriptExists = document.querySelector(
        "script[src='js/append.js']"
      );
      const gioHangScriptExists = document.querySelector(
        "script[src='js/giohang.js']"
      );

      // Nếu script chưa được thêm vào trang, thêm chúng vào
      if (!appendScriptExists) {
        const appendScript = document.createElement("script");
        appendScript.src = "js/append.js";
        document.body.appendChild(appendScript);
      }

      if (!gioHangScriptExists) {
        const gioHangScript = document.createElement("script");
        gioHangScript.src = "js/giohang.js";
        document.body.appendChild(gioHangScript);
      }
    });
  })
  .catch((error) => {
    console.error("Error fetching categories:", error);
  });

  });
  