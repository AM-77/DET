import { format_machine } from "./machine.template.js"
$(document).ready(() => {
    //* The machines slider
    const slider = (selector, items_0, items_900, items_1200) => {
        $(selector).owlCarousel({
            nav: true,
            navText: ['<img src="/assets/images/arrowhead-left.svg" alt="prev" />', '<img src="/assets/images/arrowhead-right.svg" alt="next" />'],
            mouseDrag: true,
            touchDrag: true,
            pullDrag: true,
            rtl: false,
            loop: true,
            margin: 1,
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
    slider('.machines .owl-carousel', 1, 2, 3)

    //* active links 
    const set_active_page = () => {
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

    //* grid / list view
    if ($(".machines-container .sidebar")) {
        $(".machines-container .sidebar img.grid-view").click(() => { $(".machines-container .sidebar").removeClass("list").addClass("grid") })
        $(".machines-container .sidebar img.list-view").click(() => { $(".machines-container .sidebar").removeClass("grid").addClass("list") })
    }

    let active_item = {}
    //* select a mechine from
    const set_active_item = () => {
        $(".sidebar .machines .machine").click(function (e) {
            $(".sidebar .machines .machine.active").removeClass("active")
            $(this).addClass("active")
            const machine_name = $(this).find("span").text()
            active_item.machine_img = $(this).find("img").attr("src")
            fetch(`${location.href}/${$(this).attr("data-map-name")}`)
                .then(res => res.json())
                .then(res => {
                    console.log(res)
                    $(".machines-container .content").html(format_machine(machine_name, res))
                })
        })
    }

    let machines_list = []
    //* display sidbar machines
    const format_html = (machines) => {
        let machines_html = ""
        machines.forEach(machine => {
            machines_html += `<div class="machine" data-map-name=${machine.mapName}><div><img src="${location.origin}/${machine.imageName}" alt="${machine.machineName} machine" /><span>${machine.machineName}</span></div></div>`
        })
        if (machines_html === "") machines_html = `<div class="machine no-machine"><span>No Machine Was Found</span></div></div>`
        $(".sidebar .machines").html(machines_html)
        set_active_item()
    }

    //* search a machine
    let search_input = $(".machines-container .sidebar .head .search input")

    search_input.keyup(e => {
        let value = e.target.value.trim().toLowerCase()
        if (value !== "") format_html(find_machine(value, machines_list))
        else format_html(machines_list)
    })

    const find_machine = (machine_name, machines) => {
        let found_machines = []
        for (let i = 0; i < machines.length; i++)
            if (machines[i].machineName.toLowerCase().indexOf(machine_name) >= 0)
                found_machines.push(machines[i])
        return found_machines
    }

    // fetching data from the API
    const display_sidebar = (pathname) => {
        fetch(`${location.origin}/Search/${pathname.slice(pathname.lastIndexOf("/") + 1)}`)
            .then(res => res.json())
            .then(res => {
                machines_list = res
                format_html(machines_list)
            })
    }
    display_sidebar(location.pathname)
})