document.addEventListener("DOMContentLoaded", function() {
            const sections = document.querySelectorAll("section");

            function checkSectionInView() {
                sections.forEach(section => {
                    const sectionTop = section.getBoundingClientRect().top;
                    const sectionBottom = section.getBoundingClientRect().bottom;

                    if (sectionTop < window.innerHeight && sectionBottom >= 0) {
                        section.classList.add("active");
                    } else {
                        section.classList.remove("active");
                    }
                });
            }

            window.addEventListener("scroll", checkSectionInView);
            window.addEventListener("resize", checkSectionInView);

            checkSectionInView();
        });