// JavaScript code for fetching categories, pagination, and handling add functionality

function fetchProducts() {
  var staticUrl = "http://localhost:8080/api/home/file";
  fetch("http://localhost:8080/api/home/category")
    .then((response) => response.json())
    .then((data) => {
      // Clear existing table rows
      const tableBody = document.getElementById("productTableBody");
      tableBody.innerHTML = "";

      const selectCategory = document.getElementById("category");
      selectCategory.innerHTML = "";

      const selectCategoryEdit = document.getElementById("categoryEdit");
      selectCategory.innerHTML = "";

      // Populate table rows with data
      data.data.forEach((category, index) => {
        const option = `<option>${category.name}</option>`;
        selectCategory.innerHTML += option;
        selectCategoryEdit.innerHTML += option;
        category.foodDTOList.forEach((food) => {
          const row = `
        <tr>
            <td>${food.id}</td>
            
            <td>
                <img src="${staticUrl}/food/${food.image}" alt="anh" width="100px" height="100px" style="object-fit: contain" />
            </td>
            <td>${food.title}</td>
            <td>${category.name}</td>                                    
            <td>${food.price}</td> 
            <td>
              <button class="btn btn-primary editModalBtn">
                  <i class="fas fa-edit"></i>
              </button>
              <button class="btn btn-danger deleteModalBtn">
                  <i class="fas fa-trash"></i>
              </button>
            </td>
        </tr>
              `;
          tableBody.innerHTML += row;
        });
      });
    })
    .catch((error) => console.error("Error fetching data:", error));
}

// hiển thị danh sách các sản phẩm
document.addEventListener("DOMContentLoaded", fetchProducts);

// Document ready function
$(document).ready(function () {
  $(document).ready(function () {
    // hiển thị modal thêm mới sản phẩm
    $("#addModalBtn").click(function () {
      // Hiển thị modal
      $("#addProductModal").modal("show");
    });
  });

  // Khi nút "Close" để đóng modal
  $("#addProductModal")
    .find(".modal-footer #closeBtn")
    .click(function () {
      document.getElementById("nameProduct").value = "";
      document.getElementById("priceProduct").value = "";
      $("#imagePreview img").attr("src", "");
      // Đặt lại giá trị của input type=file về rỗng
      $("#imageInput").val("");
      $("#productImage").removeClass("preview-image");
      document.getElementById("category").value = "Bữa sáng";
      // Đóng modal
      $("#addProductModal").modal("hide");
    });

  $("#addProductModal")
    .find(".modal-header .close")
    .click(function () {
      document.getElementById("nameProduct").value = "";
      document.getElementById("priceProduct").value = "";
      $("#imagePreview img").attr("src", "");
      // Đặt lại giá trị của input type=file về rỗng
      $("#imageInput").val("");
      $("#productImage").removeClass("preview-image");
      document.getElementById("category").value = "Bữa sáng";
      // Đóng modal
      $("#addProductModal").modal("hide");
    });

  // lưu sản phẩm mới khi ấn lưu
  let fileName;
  const imageInput = document.getElementById("imageInput");
  
  imageInput.addEventListener("change", function () {
    const files = imageInput.files[0];
    fileName = files.name;
  });
  $("#saveBtn").click(function () {
    let nameProduct = $("#nameProduct").val();
    let priceProduct = $("#priceProduct").val();
    let category = $("#category").val();

    console.log(imageInput);

    if (
      nameProduct !== "" &&
      priceProduct !== "" &&
      category !== "" &&
      fileName !== undefined
    ) {
      // Lấy token từ localStorage
      const token = localStorage.getItem("token");
      // Thêm token vào header
      const headers = {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      };

      // goi api them moi category
      fetch("url_của_api_thêm_mới", {
        method: "POST",
        headers: headers,
        body: JSON.stringify({
          file: fileName,
          title: nameProduct,
          timeShip: "Khoảng 30 phút",
          price: priceProduct,
          category_name: category,
        }),
      })
        .then((response) => response.json())
        .then((data) => {
          // Kiểm tra phản hồi từ API
          if (data.success) {
            // Cập nhật lại danh sách danh mục trên giao diện
            fetchCategories();
            // Đóng modal sau khi thêm mới thành công
            $("#addModal").modal("hide");
          } else {
            // Xử lý lỗi nếu có
            alert("Có lỗi xảy ra khi thêm mới danh mục.");
          }
        })
        .catch((error) => {
          // Xử lý lỗi khi gọi API
          alert("Đã xảy ra lỗi khi gọi API thêm mới danh mục.");
          console.error(error);
        });
    }
  });

  // Handle click event for edit buttons using class selector
  $(document).on("click", ".editModalBtn", function (event) {
    // if (event.target && event.target.class === "editModalBtn") {
    // Lấy dữ liệu từ hàng sản phẩm tương ứng
    const row = event.target.closest("tr");
    const productId = row.querySelector("td:nth-child(1)").textContent;
    const productName = row.querySelector("td:nth-child(3)").textContent;
    const categoryName = row.querySelector("td:nth-child(4)").textContent;
    const productPrice = row.querySelector("td:nth-child(5)").textContent;
    const productImage = row.querySelector("img").src;
    
    const contentAfterImages = productImage.split("images/")[1];    
    
    // Gán giá trị vào các trường của form edit
    document.getElementById("productNameEdit").value = productName;
    document.getElementById("productPriceEdit").value = productPrice;
    document.getElementById("productImageEdit").src = productImage;
    document.getElementById("categoryEdit").value = categoryName;

    // Hiển thị modal editProduct
    $("#editProductModal").modal("show");
    $("#editProductModal").data("productId", productId);
    $("#editProductModal").data("fileName", contentAfterImages);
    // }
  });

  // Khi nút "Close" trong modal được nhấn
  $("#editProductModal")
    .find(".modal-footer #closeBtnEdit")
    .click(function () {
      // Đóng modal
      $("#editProductModal").modal("hide");
    });

  $("#editProductModal")
    .find(".modal-header .close")
    .click(function () {
      // Đóng modal
      $("#editProductModal").modal("hide");
    });

  //ấn vào nút update để cập nhật
  // lưu sản phẩm mới khi ấn lưu
  let fileNameEdit;
  const imageInputEdit = document.getElementById('imageInputEdit');

  imageInputEdit.addEventListener("change", function () {
    const files = imageInput.files[0];
    fileNameEdit = files.name;
  });
  $("#updateBtn").click(function () {
    let nameProduct = $("#productNameEdit").val();
    let priceProduct = $("#productPriceEdit").val();
    let category = $("#categoryEdit").val();
    let fileName = $("#editProductModal").data("fileName");
    if(fileNameEdit === undefined){
      fileNameEdit = fileName;
    }    

    if (
      nameProduct !== "" &&
      priceProduct !== "" &&
      category !== "" &&
      fileNameEdit !== undefined
    ) {
      // Lấy token từ localStorage
      const token = localStorage.getItem("token");
      // Thêm token vào header
      const headers = {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      };

      // goi api them moi category
      fetch("url_của_api_thêm_mới", {
        method: "PUT",
        headers: headers,
        body: JSON.stringify({
          file: fileName,
          title: nameProduct,
          timeShip: "Khoảng 30 phút",
          price: priceProduct,
          category_name: category,
        }),
      })
        .then((response) => response.json())
        .then((data) => {
          // Kiểm tra phản hồi từ API
          if (data.success) {
            // Cập nhật lại danh sách danh mục trên giao diện
            fetchCategories();
            // Đóng modal sau khi thêm mới thành công
            $("#addModal").modal("hide");
          } else {
            // Xử lý lỗi nếu có
            alert("Có lỗi xảy ra khi thêm mới danh mục.");
          }
        })
        .catch((error) => {
          // Xử lý lỗi khi gọi API
          alert("Đã xảy ra lỗi khi gọi API thêm mới danh mục.");
          console.error(error);
        });
    }
  });

  // Handle click event for delete buttons using class selector
  $(document).on("click", ".deleteModalBtn", function (event) {
    const row = event.target.closest("tr");
    const productId = row.querySelector("td:nth-child(1)").textContent;    

    // Hiển thị modal
    $("#deleteProductModal").modal("show");
    $("#deleteProductModal").data("productId", productId);
  });

  // Khi nút "Close" trong modal được nhấn
  $("#deleteProductModal")
    .find(".modal-footer #closeBtnDelete")
    .click(function () {
      // Đóng modal
      $("#deleteProductModal").modal("hide");
    });

  $("#deleteProductModal")
    .find(".modal-header .close")
    .click(function () {
      // Đóng modal
      $("#deleteProductModal").modal("hide");
    });

  $("#deleteProductModal")
    .find("#confirmDeleteBtn")
    .click(function (event) {
      // Lấy tên của category từ thuộc tính data
      let productId = $("#deleteProductModal").data("productId");      

      // Lấy token từ localStorage
      const token = localStorage.getItem("token");
      // Thêm token vào header
      const headers = {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      };

      // Gọi API để xóa category
      fetch("url_cua_api_xoa_category", {
        method: "DELETE",
        headers: headers,
        body: JSON.stringify({ id: productId }), // Truyền ID của category cần xóa vào trong request body
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.success) {
            // goi lại api
            fetchProducts();
            // Ẩn modal xác nhận
            $("#deleteProductModal").modal("hide");
          } else {
            throw new Error(data.desc || "Có lỗi xảy ra khi xóa category.");
          }
        })
        .catch((error) => {
          console.error("Lỗi khi xóa category:", error);
        });
    });
});
