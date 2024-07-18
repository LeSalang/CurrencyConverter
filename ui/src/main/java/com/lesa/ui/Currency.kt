package com.lesa.ui

enum class Currency(
    val code: String,
    val fullName: String,
    val country: String,
    val drawableRes: Int
) {
    AUD(
        code = "AUD",
        fullName = "Australian Dollar",
        country = "Australia",
        drawableRes = R.drawable.australia_flag_circular
    ),
    BGN(
        code = "BGN",
        fullName = "Bulgarian Lev",
        country = "Bulgaria",
        drawableRes = R.drawable.bulgaria_flag_circular
    ),
    BRL(
        code = "BRL",
        fullName = "Brazilian Real",
        country = "Brazil",
        drawableRes = R.drawable.brazil_flag_circular
    ),
    CAD(
        code = "CAD",
        fullName = "Canadian Dollar",
        country = "Canada",
        drawableRes = R.drawable.canada_flag_circular
    ),
    CHF(
        code = "CHF",
        fullName = "Swiss Franc",
        country = "Switzerland",
        drawableRes = R.drawable.switzerland_flag_circular
    ),
    CNY(
        code = "CNY",
        fullName = "Chinese Yuan",
        country = "China",
        drawableRes = R.drawable.china_flag_circular
    ),
    CZK(
        code = "CZK",
        fullName = "Czech Republic Koruna",
        country = "Czech_Republic",
        drawableRes = R.drawable.czech_republic_flag_circular
    ),
    DKK(
        code = "DKK",
        fullName = "Danish Krone",
        country = "Denmark",
        drawableRes = R.drawable.denmark_flag_circular
    ),
    EUR(
        code = "EUR",
        fullName = "Euro",
        country = "European_Union",
        drawableRes = R.drawable.european_union_flag_circular
    ),
    GBP(
        code = "GBP",
        fullName = "British Pound Sterling",
        country = "United_Kingdom",
        drawableRes = R.drawable.uk_flag_circular
    ),
    HUF(
        code = "HUF",
        fullName = "Hungarian Forint",
        country = "Hungary",
        drawableRes = R.drawable.hungary_flag_circular
    ),
    ILS(
        code = "ILS",
        fullName = "Israeli New Sheqel",
        country = "Israel",
        drawableRes = R.drawable.israel_flag_circular
    ),
    INR(
        code = "INR",
        fullName = "Indian Rupee",
        country = "India",
        drawableRes = R.drawable.india_flag_circular
    ),
    ISK(
        code = "ISK",
        fullName = "Icelandic Króna",
        country = "Iceland",
        drawableRes = R.drawable.iceland_flag_circular
    ),
    JPY(
        code = "JPY",
        fullName = "Japanese Yen",
        country = "Japan",
        drawableRes = R.drawable.japan_flag_circular
    ),
    KRW(
        code = "KRW",
        fullName = "South Korean Won",
        country = "South_Korea",
        drawableRes = R.drawable.south_korea_flag_circular
    ),
    MXN(
        code = "MXN",
        fullName = "Mexican Peso",
        country = "Mexico",
        drawableRes = R.drawable.mexico_flag_circular
    ),
    MYR(
        code = "MYR",
        fullName = "Malaysian Ringgit",
        country = "Malaysia",
        drawableRes = R.drawable.malaysia_circle_round_flag
    ),
    NOK(
        code = "NOK",
        fullName = "Norwegian Krone",
        country = "Norway",
        drawableRes = R.drawable.norway_flag_circular
    ),
    NZD(
        code = "NZD",
        fullName = "New Zealand Dollar",
        country = "New_Zealand",
        drawableRes = R.drawable.new_zealand_flag_circular
    ),
    PHP(
        code = "PHP",
        fullName = "Philippine Peso",
        country = "Philippines",
        drawableRes = R.drawable.philiphines_flag_circular
    ),
    PLN(
        code = "PLN",
        fullName = "Polish Zloty",
        country = "Poland",
        drawableRes = R.drawable.poland_flag_circular
    ),
    RON(
        code = "RON",
        fullName = "Romanian Leu",
        country = "Romania",
        drawableRes = R.drawable.romania_flag_circular
    ),
    RUB(
        code = "RUB",
        fullName = "Russian Ruble",
        country = "Russia",
        drawableRes = R.drawable.russia_flag_circular
    ),
    SEK(
        code = "SEK",
        fullName = "Swedish Krona",
        country = "Sweden",
        drawableRes = R.drawable.sweeden_flag_circular
    ),
    SGD(
        code = "SGD",
        fullName = "Singapore Dollar",
        country = "Singapore",
        drawableRes = R.drawable.singapore_flag_circular
    ),
    THB(
        code = "THB",
        fullName = "Thai Baht",
        country = "Thailand",
        drawableRes = R.drawable.thailand_flag_circular
    ),
    TRY(
        code = "TRY",
        fullName = "Turkish Lira",
        country = "Turkey",
        drawableRes = R.drawable.turkey_flag_circular
    ),
    USD(
        code = "USD",
        fullName = "US Dollar",
        country = "United_States",
        drawableRes = R.drawable.usa_flag_circular
    ),
    ZAR(
        code = "ZAR",
        fullName = "South African Rand",
        country = "South_Africa",
        drawableRes = R.drawable.south_africa_flag_circular
    )
}
