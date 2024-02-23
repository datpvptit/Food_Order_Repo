$(document).ready(function () {
  $("#show_hide_password a").on("click", function (event) {
    event.preventDefault();
    if ($("#show_hide_password input").attr("type") === "text") {
      $("#show_hide_password input").attr("type", "password");
      $("#show_hide_password i").addClass("fa-eye-slash");
      $("#show_hide_password i").removeClass("fa-eye");
    } else if ($("#show_hide_password input").attr("type") === "password") {
      $("#show_hide_password input").attr("type", "text");
      $("#show_hide_password i").removeClass("fa-eye-slash");
      $("#show_hide_password i").addClass("fa-eye");
    }
  });
});

document.addEventListener("DOMContentLoaded", function () {
  const loginForm = document.getElementById("loginForm");

  loginForm.addEventListener("submit", function (event) {
    event.preventDefault(); // Ngăn chặn form gửi dữ liệu theo cách thông thường
    const emailIn = document.getElementById("email").value; // Lấy giá trị của trường email
    const passwordIn = document.getElementById("password").value; // Lấy giá trị của trường password
    const data = {email:emailIn, password:passwordIn}
    if (email === "" || password === "") {
      alert("Vui lòng điền thông tin");
    } else {
      fetch("http://localhost:8080/api/user/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            // 'Content-Type': 'application/x-www-form-urlencoded',
          },
          body: JSON.stringify(data),
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.success) {
            sessionStorage.clear();
            sessionStorage.setItem("user", emailIn);
            sessionStorage.setItem("token", data.data);
            sessionStorage.setItem("id", data.id);
            localStorage.clear();
            // Kiểm tra quyền truy cập và điều hướng người dùng
            const desc = data.desc;
            if (desc === "[ADMIN]") {
              // Điều hướng đến trang quản trị
              window.location.href = "admin.html";
            } else {
              // Điều hướng đến trang người dùng thông thường
              window.location.href = "menu.html";
            }
          } else {
            // Xử lý lỗi khi đăng nhập không thành công
            console.error("Đăng nhập thất bại:", data.error);
          }
        })
        .catch((error) => {
          console.error("Lỗi gọi API:", error);
        });
    }
  });
});
