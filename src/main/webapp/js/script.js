function init() {
    window.addEventListener('scroll', function (e) {
        var distanceY = window.pageYOffset || document.documentElement.scrollTop,
            shrinkOn = 100,
            nav = document.querySelector("nav"),
            profile = document.querySelector(".profile");
        if (distanceY > shrinkOn) {
            classie.remove(nav, "complex-navbar");
            classie.add(nav, "simple-navbar");
            classie.remove(profile, "dropup");
            classie.add(profile, "dropdown");
        } else {
            classie.remove(nav, "simple-navbar");
            classie.add(nav, "complex-navbar");
            classie.remove(profile, "dropdown");
            classie.add(profile, "dropup");
        }
    });
}
window.onload = init();

function showForm(event) {
    var activeForm = document.querySelector(".active"),
        inactiveForm = document.querySelector(".inactive"),
        activeTab  = document.querySelector(".nav-menu-item-active"),
        inactiveTab  = document.querySelector(".nav-menu-item-inactive");

    classie.remove(activeForm, "active");
    classie.add(activeForm, "inactive");
    classie.remove(inactiveForm, "inactive");
    classie.add(inactiveForm, "active");

    classie.remove(activeTab, "nav-menu-item-active");
    classie.add(activeTab, "nav-menu-item-inactive");
    classie.remove(inactiveTab, "nav-menu-item-inactive");
    classie.add(inactiveTab, "nav-menu-item-active");
}
