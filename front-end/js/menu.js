document.addEventListener("DOMContentLoaded", function () {
  var staticUrl = "http://localhost:8080/api/home/file";
  // Gọi API để lấy danh sách các category
  fetch("http://localhost:8080/api/home/category")
    .then((response) => response.json())
    .then((data) => {
      // Lặp qua từng category và hiển thị vào HTML
      data.data.forEach((category, index) => {
        let categoryName = category.name;
        let categoryLink = index + 1;

        // Tạo một tab cho mỗi category
        let tab = document.createElement("a");
        tab.classList.add(
          "nav-link",
          "ftco-animate",
          "fadeInUp",
          "ftco-animated"
        );
        tab.setAttribute("id", `v-pills-${categoryLink}-tab`);
        tab.setAttribute("data-toggle", "pill");
        tab.setAttribute("href", `#v-pills-${categoryLink}`);
        tab.setAttribute("role", "tab");
        tab.setAttribute("aria-controls", `v-pills-${categoryLink}`);
        tab.setAttribute("aria-selected", "false");
        tab.textContent = categoryName;

        // Thêm lớp active cho tab đầu tiên
        if (index === 0) {
          tab.classList.add("active");
        }

        // Thêm tab vào danh sách các tab
        document.getElementById(`v-pills-tab`).appendChild(tab);

        // Tạo một tab-pane cho mỗi category để hiển thị danh sách món ăn
        let tabPane = document.createElement("div");
        tabPane.classList.add("tab-pane", "fade");
        tabPane.setAttribute("id", `v-pills-${categoryLink}`);
        tabPane.setAttribute("role", "tabpanel");
        tabPane.setAttribute("aria-labelledby", `day-${categoryLink}-tab`);

        if (index === 0) {
          tabPane.classList.add("active", "show");
        }

        let row = document.createElement("div");
        row.classList.add("row", "no-gutters", "d-flex", "align-items-stretch");

        // Lặp qua từng món ăn trong category và hiển thị vào danh sách món ăn
        category.foodDTOList.forEach((food, index) => {
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
                    <div class="${menuImgClass}" style="background-image: url(${staticUrl}/food/${food.image});"></div>
                    <div class="text d-flex align-items-center" id=${food.id}>
												<div>
													<div class="d-flex">
														<div class="one-half">
															<h3>${food.title}</h3>
                            
														</div>
														<div class="one-forth">
															<span class="price">$${food.price}</span>
														</div>
													</div>
													<p><span>${food.material}</span>
													</p>
													<p><button class="btn btn-primary addBtn">Thêm món</button></p>
												</div>
											</div>
                    
                `;
          col.appendChild(foodItem);
          row.appendChild(col);
        });

        // Thêm danh sách món ăn vào tab-pane
        tabPane.appendChild(row);

        // Thêm tab-pane vào tab-content
        document.getElementById("v-pills-tabContent").appendChild(tabPane);

      });
    })
    .catch((error) => {
      console.error("Error fetching categories:", error);
    });
});
