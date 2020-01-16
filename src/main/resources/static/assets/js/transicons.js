/*! Transicons | AM-77 https://github.com/AM-77/css-trix/tree/master/Transicons | MIT/license */
transformicons = document.querySelectorAll('.transicon')
if (transformicons)
    transformicons.forEach(transformicon => {
        transformicon.addEventListener("click", function () {
            if (this.classList.contains("open")) {
                this.classList.add("close")
                this.classList.remove("open")
            } else {
                this.classList.add("open")
                this.classList.remove("close")
            }
        })
    })