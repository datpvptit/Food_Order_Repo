// JavaScript code for fetching categories, pagination, and handling add functionality

// Function to fetch categories from server
function fetchAccount(page) {
    // Implement your logic here to fetch categories from the server
    // You can use AJAX to make a request to your server-side endpoint
    // and then populate the table with the fetched data
  }
  
  // Function to handle add category functionality
  function addAccont() {
    // Implement your logic here to handle adding a new category
    // This function should be triggered when the user clicks the Save button
    // Inside this function, you can retrieve the input values from the modal
    // and then send them to the server using AJAX for processing
  }
  
  // Document ready function
  $(document).ready(function () {
    // Fetch categories on page load
    // fetchCategories(1);
  
    $(document).ready(function () {
      // Khi nút "Thêm mới danh mục" được nhấn
      $("#addModalBtn").click(function () {
        // Hiển thị modal
        $("#addAccountModal").modal("show");
      });
    });
  
    // Khi nút "Close" trong modal được nhấn
    $("#addAccountModal")
      .find(".modal-footer #closeBtn")
      .click(function () {
        // Đóng modal
        $("#addAccountModal").modal("hide");
      });
  
    $("#addAccountModal")
      .find(".modal-header .close")
      .click(function () {
        // Đóng modal
        $("#addAccountModal").modal("hide");
      });
  
    //   // Lấy tất cả các thẻ ảnh trong navbar
    //   let navImages = document.querySelectorAll("#sidebar a");
  
    //   console.log(navImages);
  
    //   // Lặp qua từng thẻ ảnh
    //   navImages.forEach(function (image) {
    //     // Thêm sự kiện click cho mỗi thẻ ảnh
    //     image.addEventListener("click", function () {
    //       // Loại bỏ thuộc tính "active" khỏi tất cả các thẻ ảnh
    //       navImages.forEach(function (image) {
    //         image.classList.remove("active");
    //       });
    //       // Thêm thuộc tính "active" vào thẻ ảnh được nhấn vào
    //       this.classList.add("active");
    //     });
    //   });
  
    // Search button click event
    // $('#searchBtn').click(function(e) {
    //     e.preventDefault();
    //     var searchText = $('#searchInput').val();
    //     // Implement search functionality here
    //     // You can send the search text to the server using AJAX
    //     // and update the table with the filtered results
    // });
  
    // Save button click event for adding category
    // $('#saveBtn').click(function() {
    //     addCategory();
    // });
  });
  