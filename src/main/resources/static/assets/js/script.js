//* The sliders
function _slider(selector, items_0, items_900, items_1200) {
    $(selector).owlCarousel({
        nav: true,
        navText: ['<img src="/assets/images/arrowhead-left.svg" alt="prev" />', '<img src="/assets/images/arrowhead-right.svg" alt="next" />'],
        mouseDrag: true,
        touchDrag: true,
        pullDrag: true,
        rtl: false,
        loop: true,
        margin: 00,
        items: 3,
        autoplay: true,
        smartSpeed: 1000,
        dots: true,
        responsiveClass: true,
        responsive: {
            0: {
                items: items_0
            },
            900: {
                items: items_900
            },
            1200: {
                items: items_1200
            }
        }
    })
}

//* The machines slider
_slider('.machines .owl-carousel', 1, 2, 3)