$(document).ready(() => {
    //* The sliders
    let _slider = (selector, items_0, items_900, items_1200) => {
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

    //* active links 
    let set_active_page = () => {
        let links = ["home", "centre", "tour", "contact"]
        links.forEach(link => $(".navbar a.link." + link).removeClass("active"))
        switch (location.pathname) {
            case "/":
                $(".navbar a.link.home").addClass("active")
                break;
            case "/Category/centre":
                $(".navbar a.link.centre").addClass("active")
                break;
            case "/Category/tour":
                $(".navbar a.link.tour").addClass("active")
                break;
            case "/contact":
                $(".navbar a.link.contact").addClass("active")
                break;
            default:
                break;
        }
    }
    set_active_page()

    // grid / list view
    if ($(".machines-container .sidebar")) {
        $(".machines-container .sidebar img.grid-view").on("click", () => {
            $(".machines-container .sidebar").removeClass("grid").addClass("list")
        })

        $(".machines-container .sidebar img.list-view").on("click", () => {
            $(".machines-container .sidebar").removeClass("list").addClass("grid")
        })
    }
})