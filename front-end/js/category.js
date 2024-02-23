// JavaScript code for fetching categories, pagination, and handling add functionality
function fetchCategories() {
  fetch("http://localhost:8080/api/home/category")
    .then((response) => response.json())
    .then((data) => {
      // Clear existing table rows
      const tableBody = document.getElementById("categoryTableBody");
      tableBody.innerHTML = "";

      // Populate table rows with data
      data.data.forEach((category, index) => {
        const row = `
                <tr>
                    <td>${index + 1}</td>
                    <td class=categoryName>${category.name}</td>
                    
                    <td>
                        <button class="btn btn-primary editModalBtn" data-category-name="${
                          category.name
                        }">
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
    })
    .catch((error) => console.error("Error fetching data:", error));
}

// hiển thị danh sách category 
document.addEventListener("DOMContentLoaded", fetchCategories);


// Document ready function
$(document).ready(function () {  

  // modal add category
  $(document).ready(function () {

    // Khi nút "Thêm mới danh mục" được nhấn
    $("#addModalBtn").click(function () {
      // Hiển thị modal
      $("#addModal").modal("show");
    });
  });

  // Khi nút "Close" trong modal được nhấn
  $("#addModal")
    .find(".modal-footer #closeBtn")
    .click(function () {
      // Đóng modal
      $("#addModal").modal("hide");
    });

  $("#addModal")
    .find(".modal-header .close")
    .click(function () {
      // Đóng modal
      $("#addModal").modal("hide");
    });

  // lưu category mới khi ấn lưu
  $("#saveBtn").click(function () {
    let categoryName = $("#tenDanhMuc").val();

    // Lấy token từ localStorage
    const token = localStorage.getItem("token");

    // Thêm token vào header
    const headers = {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    };

    if (
      categoryName !== "" &&
      categoryName !== undefined &&
      categoryName !== null
    ) {
      // goi api them moi category
      fetch("url_của_api_thêm_mới", {
        method: "POST",
        headers: headers,
        body: JSON.stringify({ name: categoryName }),
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
  $(document).on("click", ".editModalBtn", function () {
    // Hiển thị modal
    $("#editModal").modal("show");
    // Lấy giá trị tên danh mục từ thuộc tính dữ liệu của nút chỉnh sửa
    let categoryName = $(this).data("category-name");
    
    $("#editModal").data("oldNameCategory", categoryName);

    // Đặt giá trị mặc định cho input trong modal chỉnh sửa
    $("#tenDanhMucEdit").val(categoryName);
  });

  // Khi nút "Close" trong modal được nhấn
  $("#editModal")
    .find(".modal-footer #closeBtnEdit")
    .click(function () {
      // Đóng modal
      $("#editModal").modal("hide");
    });

  $("#editModal")
    .find(".modal-header .close")
    .click(function () {
      // Đóng modal
      $("#editModal").modal("hide");
    });

  //ấn vào nút update để cập nhật
  $("#updateBtn").click(function () {
    let newNameCategory = $("#tenDanhMucEdit").val();
    let oldNameCategory = $("#editModal").data("oldNameCategory");

    if (newNameCategory !== oldNameCategory) {
      
      // Lấy token từ localStorage
      const token = localStorage.getItem("token");

      // Thêm token vào header
      const headers = {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      };

      // Gọi API để xóa category
      fetch("url_cua_api_xoa_category", {
        method: "PUT",
        headers: headers,
        body: JSON.stringify({ name: oldNameCategory, newName: newNameCategory })
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.success) {
            // goi lại api
            fetchCategories();
            // Ẩn modal xác nhận
            $("#editModal").modal("hide");
          } else {
            throw new Error(data.desc || "Có lỗi xảy ra khi xóa category.");
          }
        })
        .catch((error) => {
          console.error("Lỗi khi xóa category:", error);
        });
    } else {
      $("#editModal").modal("hide");
    }
  });

  // Handle click event for delete buttons using class selector
  $(document).on("click", ".deleteModalBtn", function (event) {
    let categoryName = $(this).closest("tr").find(".categoryName").text();
    // Hiển thị modal delete
    $("#deleteModal").modal("show");
    $("#deleteModal").data("categoryName", categoryName);
  });

  // Khi nút "Close" trong modal được nhấn
  $("#deleteModal")
    .find(".modal-footer #closeBtnDelete")
    .click(function () {
      // Đóng modal

      $("#deleteModal").modal("hide");
    });

  $("#deleteModal")
    .find(".modal-header .close")
    .click(function () {
      // Đóng modal
      $("#deleteModal").modal("hide");
    });

  $("#deleteModal")
    .find("#confirmDeleteBtn")
    .click(function (event) {
      // Lấy tên của category từ thuộc tính data
      let categoryName = $("#deleteModal").data("categoryName");

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
        body: JSON.stringify({ name: categoryName })
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.success) {
            // goi lại api
            fetchCategories();
            // Ẩn modal xác nhận
            $("#deleteModal").modal("hide");
          } else {
            throw new Error(data.desc || "Có lỗi xảy ra khi xóa category.");
          }
        })
        .catch((error) => {
          console.error("Lỗi khi xóa category:", error);
        });
    });
});
