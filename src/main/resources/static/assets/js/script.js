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
        $(".machines-container .sidebar img.grid-view").click(() => {
            $(".machines-container .sidebar").removeClass("list").addClass("grid")
        })

        $(".machines-container .sidebar img.list-view").click(() => {
            $(".machines-container .sidebar").removeClass("grid").addClass("list")
        })
    }

    //* display sidbar machines
    let machines = $(".sidebar ._machines .machine")

    let format_html = (machines) => {
        let machines_html = ""

        for (let i = 0; i < machines.length; i++) {
            let img_src = $(machines[i]).find("img").attr("src").trim()
            let machine_name = $(machines[i]).find("span").text().trim()

            machines_html += `<div class="machine"><div><img src="${img_src}" alt="${machine_name} machine" /><span>${machine_name}</span></div></div>`
        }
        return machines_html
    }

    $(".sidebar .machines").html(format_html(machines))

    //* search a machine
    let search_input = $(".machines-container .sidebar .head .search input")

    search_input.keyup(e => {
        let value = e.target.value.trim().toLowerCase()
        if (value !== "") {
            $(".sidebar .machines").html(format_html(find_machine(value, machines)))
        } else {
            $(".sidebar .machines").html(format_html(machines))
        }
    })

    let find_machine = (machine_name, machines) => {
        let found_machines = []
        for (let i = 0; i < machines.length; i++)
            if ($(machines[i]).find("span").text().trim().toLowerCase().indexOf(machine_name) >= 0)
                found_machines.push($(machines[i]))
        return found_machines
    }

    // select a mechine from
    $(".sidebar .machines .machine").click(function (e) {
        console.log($(this));
        $(".sidebar .machines .machine.active").removeClass("active")
        $(this).addClass("active")
    })

})