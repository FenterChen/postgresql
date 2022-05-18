// const colors = require("tailwindcss/colors");

module.exports = {
    content: ["./src/**/*.{html,js}"],
    darkMode: false, // or 'media' or 'class'
    // content: ["./src/**/*.{html,js,vue}"],
    purge: false,
    theme: {
        extend: {
            fontSize: {
                slogan: "48px",
            },
            maxWidth: {
                "8xl": "1320px",
            },
            width: {
                "3/50": "6%",
            },
            height: {
                "xl-height": "790px",
                "lg-height": "698px",
                "md-height": "488px",
                "sm-height": "420px",
                "phone-height": "290px",
            },
            screens: {
                xs: { min: "400px", max: "767px" },
            },
            spacing: {
                98: "25rem",
                100: "26rem",
            },
            colors: {
                'platinum': "rgb(229, 229, 229)",
                'regal-blue': '#243c5a',
                'sky': 'rgb(56, 189, 248)',
                'sky-600': 'rgb(2, 132, 199)',
                'sky-500': 'rgb(14, 165, 233)',
                'cyan': 'rgb(6,182,212)',
            },
        },
    },
    plugins: [require('flowbite/plugin')],
};
