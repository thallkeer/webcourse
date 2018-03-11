// var a;

var first = [];

first['Проект'] = ['ГСМ','Аренда техники','Транспортные услуги','Материалы'];
first['Офис'] = ['ГСМ','Канцтовары','Транспортные расходы','Еда и напитки'];
first['Производство'] = [];

// var second = [];
//
// second['Проект'] = [];
// second['Проект']['ГСМ'] = ['Мототехника','Автотехника'];
// second['Проект']['Аренда техники'] = [];
// second['Проект']['Транспортные услуги'] = ['Доставка материалов','Доставка писем','Доставка посылок'];
// second['Проект']['Материалы'] = ['ЦПС','Щебень','Бетон'];
//
// second['Офис'] = [];
// second['Офис']['ГСМ'] = ['Автотехника'];
// second['Офис']['Канцтовары'] = [];
// second['Офис']['Еда и напитки'] = [];
// second['Офис']['Транспортные расходы'] = ['Доставка материалов','Доставка писем','Доставка посылок'];
//
// $.getJSON("resources/Output.json", function(json) {
//     console.log(json); // this will show the info it in firebug console
//     a = JSON.parse(json);
// });



function setSecond() {
    firstSel = document.getElementById("first");
    secondList = first[firstSel.value];
    changeSelect("second",secondList,secondList);
}

// function setThird() {
//     firstSel = document.getElementById("first");
//     secondSel = document.getElementById("second");
//     thirdList =
//     changeSelect("second",secondList,secondList);
//     setThird();
// }

function changeSelect(fieldID,newOptions,newValues) {
    debugger;
    selectField = document.getElementById(fieldID);
    selectField.options.length = 0;
    for (i =0;i<newOptions.length;i++){
        selectField.options[selectField.length] = new Option(newOptions[i],newValues[i]);
    }
}

function addLoadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != 'function'){
        window.onload = func;
    } else {
        window.onload = function () {
            if (oldonload){
                oldonload();
            }
            func();
        }
    }
}

addLoadEvent(function () {
    setSecond()
})
