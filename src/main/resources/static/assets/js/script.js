import {
    format_machine
} from "./machine.template.js"

$(document).ready(() => {

    //* Scroll to about
    $(".home-container .logo img.go-down").click(function () {
        $([document.documentElement, document.body]).animate({
            scrollTop: $(".about-us").offset().top
        }, 700)
    })

    //* The machines slider
    const slider = (selector, items_0, items_900, items_1200) => {
        $(selector).owlCarousel({
            nav: true,
            navText: ['<img src="assets/images/arrowhead-left.svg" alt="prev" />', '<img src="assets/images/arrowhead-right.svg" alt="next" />'],
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

    //* Show and Hide The Sidebar
    const hide_sidebar = () => {
        $(".machines-container .sidebar").animate({
            left: "-330px"
        }, 350, () => { })
        $(".machines-container .content").animate({
            width: "100%",
            marginLeft: "0px"
        }, 350, () => { })
        $(".header .show-sidebar").css({
            display: "block"
        }).animate({
            opacity: 1
        }, 350, () => { })
    }

    const show_sidebar = () => {
        $(".machines-container .sidebar").animate({
            left: "0px"
        }, 350, () => { })
        $(".machines-container .content").animate({
            width: "calc(100% - 300px)",
            marginLeft: "300px"
        }, 350, () => { })
        $(".header .show-sidebar").animate({
            opacity: 0
        }, 350, () => {
            $(".header .show-sidebar").css({
                display: "none"
            })
        })
    }

    $(".machines-container .sidebar .head .hide-sidebar").click(hide_sidebar)
    $(".header .show-sidebar").click(show_sidebar)

    //* Show and Hide The Mobile Navbar
    $(".container .header .nav-btn").click(() => {
        $(".container").hasClass("mobile") ? $(".container").removeClass("mobile") : $(".container").addClass("mobile")
    })

    //* active links 
    const set_active_page = () => {
        let links = ["home", "centre", "tour", "contact"]
        links.forEach(link => $(".navbar a.link." + link).removeClass("active"))
        switch (location.pathname) {
            case "/":
                $(".navbar a.link.home").addClass("active")
                $(".header .show-sidebar").css({
                    display: "none"
                })
                break;
            case "/Category/centre":
                $(".navbar a.link.centre").addClass("active")
                break;
            case "/Category/tour":
                $(".navbar a.link.tour").addClass("active")
                break;
            case "/contact":
                $(".navbar a.link.contact").addClass("active")
                $(".header .show-sidebar").css({
                    display: "none"
                })
                break;
            default:
                break;
        }
    }
    set_active_page()

    //* grid / list view
    if ($(".machines-container .sidebar")) {
        $(".machines-container .sidebar img.grid-view").click(() => {
            $(".machines-container .sidebar").removeClass("list").addClass("grid")
        })
        $(".machines-container .sidebar img.list-view").click(() => {
            $(".machines-container .sidebar").removeClass("grid").addClass("list")
        })
    }

    //* select a mechine from
    const set_active_item = () => {
        $(".sidebar .machines .machine").click(function (e) {
            $(".sidebar .machines .machine.active").removeClass("active")
            $(this).addClass("active")
            hide_sidebar()
            const machine_name = $(this).find("span").text()
            fetch(`${location.href}/${$(this).attr("data-map-name")}`)
                .then(res => res.json())
                .then(res => {
                    $(".machines-container .content").html(format_machine(machine_name, res))
                })
        })
    }

    // active the first machine
    const init_machine = () => {
        location.pathname === "/Category/tour" ? $(".machines-container .sidebar .head p").text("cnc lathe") : $(".machines-container .sidebar .head p").text("machining center")
        let machine = $(".sidebar .machines .machine")[0]
        $(machine).addClass("active")
        const machine_name = $(machine).find("span").text()
        fetch(`${location.href}/${$(machine).attr("data-map-name")}`)
            .then(res => res.json())
            .then(res => {
                $(".machines-container .content").html(format_machine(machine_name, res))
            })
    }

    let machines_list = []
    //* display sidbar machines
    const format_html = (machines, callback) => {
        let machines_html = ""
        machines.forEach(machine => {
            machines_html += `<div class="machine" data-map-name=${machine.mapName}><div><img src="${location.origin}/${machine.imageName}" alt="${machine.machineName} machine" /><span>${machine.machineName}</span></div></div>`
        })
        if (machines_html === "") machines_html = `<div class="machine no-machine"><span>No Machine Was Found</span></div></div>`
        $(".sidebar .machines").html(machines_html)
        set_active_item()
        callback()
    }

    //* search a machine
    let search_input = $(".machines-container .sidebar .head .search input")

    search_input.keyup(e => {
        let value = e.target.value.trim().toLowerCase()
        if (value !== "") format_html(find_machine(value, machines_list), init_machine)
        else format_html(machines_list, init_machine)
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
                format_html(machines_list, init_machine)
            })
    }

    if (location.pathname.indexOf("/Category") > -1) display_sidebar(location.pathname)

    //* handling emails
    $('.contact-container form button.send').on("click", function (e) {
        e.preventDefault()
        let name = $('.contact-container input#name').val()
        let email = $('.contact-container input#email').val()
        let message = $('.contact-container textarea#message').val()
        let confirm_message = $(".contact-container .confirm-message")
        console.log(name, email, message)

        if (email == "" || message == "") {
            confirm_message.removeClass("success")
                .addClass("error")
                .text("Please make sure to fill all the fields, Thank you.")
                .fadeIn(1000, () => { confirm_message.delay(2000).fadeOut(1000) })
            return false
        }
        else {
            $.ajax({
                type: "POST",
                url: "http://formspree.io/THE-DET-EMAIL",
                data: `name=${name}&_replyto=${email}&message=${message}`,
                dataType: "json"
            })
            confirm_message.removeClass("error")
                .addClass("success")
                .text("Your message has been successfully sent, Thank you.")
                .fadeIn(1000, () => { confirm_message.delay(2000).fadeOut(1000) })
        }
    })
})