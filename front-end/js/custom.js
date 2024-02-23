
document.addEventListener("DOMContentLoaded", function () {
    const currentPage = window.location.pathname;

    // Lấy danh sách tất cả các thẻ <a> trong thẻ có lớp "nav-item"
    const navLinks = document.querySelectorAll(".nav-item a");
    navLinks.forEach(function (link) {
        // Nếu đường dẫn của thẻ <a> chứa trong đường dẫn của trang hiện tại
        if (currentPage.includes(link.getAttribute("href"))) {
            link.parentNode.classList.add("active");
        }
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const user = sessionStorage.getItem("user");
    if (!user) {
        const navItems = document.querySelectorAll(".navbar-nav li");

        let foundLoginOrRegister = false;

        // Duyệt qua mỗi thẻ li
        navItems.forEach(function (item) {
            if (item.innerHTML.includes("Đăng nhập") || item.innerHTML.includes("Đăng ký")) {
                foundLoginOrRegister = true;
            }
            if (!item.innerHTML.includes("Đăng nhập") && !item.innerHTML.includes("Đăng ký") && foundLoginOrRegister) {
                item.style.display = "none";
            }
        });
    } else {
        // Lấy thẻ "Đăng nhập" và "Đăng ký"
        const loginItem = document.querySelector('a[href="login.html"]').parentNode;
        const registerItem = document.querySelector('a[href="register.html"]').parentNode;

        // Ẩn thẻ "Đăng nhập" và "Đăng ký"
        loginItem.style.display = "none";
        registerItem.style.display = "none";

        const logoutItem = document.createElement("li");
        logoutItem.classList.add("nav-item");
        logoutItem.innerHTML = '<a href="login.html" class="nav-link">Đăng xuất</a>';

        // Tìm thẻ navbar-nav ml-auto
        const navbarNav = document.querySelector(".navbar-nav.ml-auto");

        // Thêm thẻ "Đăng xuất" vào navbar
        navbarNav.appendChild(logoutItem);
    }
});
document.addEventListener("DOMContentLoaded", function () {
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