package com.manahu.manager.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.content.edit
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manahu.manager.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

@SuppressLint("DefaultLocale")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current as Activity
    val prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    var showDialogEndOfMonth = remember { mutableStateOf(false) }
    var showDialogSociety = remember { mutableStateOf(false) }
    var showDialogCredit = remember { mutableStateOf(false) }
    var showDialogNewGame = remember { mutableStateOf(false) }

    var isBank by remember { mutableStateOf(false) }
    var isBirga by remember { mutableStateOf(false) }
    var isHousing by remember { mutableStateOf(false) }
    var isBusiness by remember { mutableStateOf(false) }

    var isMicrosoft by remember { mutableStateOf(false) }
    var isIBM by remember { mutableStateOf(false) }
    var isGoogle by remember { mutableStateOf(false) }
    var isMicrosystem by remember { mutableStateOf(false) }
    var isNokia by remember { mutableStateOf(false) }
    var isChinaOil by remember { mutableStateOf(false) }
    var isBlackRock by remember { mutableStateOf(false) }
    var isWarpTech by remember { mutableStateOf(false) }
    var isMitsumi by remember { mutableStateOf(false) }
    var isSamsung by remember { mutableStateOf(false) }
    var isNordIce by remember { mutableStateOf(false) }
    var isCocaCola by remember { mutableStateOf(false) }

    var firstMicrosoft by remember { mutableStateOf(0) }
    var firstIBM by remember { mutableStateOf(0) }
    var firstGoogle by remember { mutableStateOf(0) }
    var firstMicrosystem by remember { mutableStateOf(0) }
    var firstNokia by remember { mutableStateOf(0) }
    var firstChinaOil by remember { mutableStateOf(0) }
    var firstBlackRock by remember { mutableStateOf(0) }
    var firstWarpTech by remember { mutableStateOf(0) }
    var firstMitsumi by remember { mutableStateOf(0) }
    var firstSamsung by remember { mutableStateOf(0) }
    var firstNordIce by remember { mutableStateOf(0) }
    var firstCocaCola by remember { mutableStateOf(0) }

    var perMicrosoft by remember { mutableStateOf(0.0) }
    var perIBM by remember { mutableStateOf(0.0) }
    var perGoogle by remember { mutableStateOf(0.0) }
    var perMicrosystem by remember { mutableStateOf(0.0) }
    var perNokia by remember { mutableStateOf(0.0) }
    var perChinaOil by remember { mutableStateOf(0.0) }
    var perBlackRock by remember { mutableStateOf(0.0) }
    var perWarpTech by remember { mutableStateOf(0.0) }
    var perMitsumi by remember { mutableStateOf(0.0) }
    var perSamsung by remember { mutableStateOf(0.0) }
    var perNordIce by remember { mutableStateOf(0.0) }
    var perCocaCola by remember { mutableStateOf(0.0) }

    var profitMicrosoft by remember { mutableStateOf(0.0) }
    var profitIBM by remember { mutableStateOf(0.0) }
    var profitGoogle by remember { mutableStateOf(0.0) }
    var profitMicrosystem by remember { mutableStateOf(0.0) }
    var profitNokia by remember { mutableStateOf(0.0) }
    var profitChinaOil by remember { mutableStateOf(0.0) }
    var profitBlackRock by remember { mutableStateOf(0.0) }
    var profitWarpTech by remember { mutableStateOf(0.0) }
    var profitMitsumi by remember { mutableStateOf(0.0) }
    var profitSamsung by remember { mutableStateOf(0.0) }
    var profitNordIce by remember { mutableStateOf(0.0) }
    var profitCocaCola by remember { mutableStateOf(0.0) }

    var capitalofMicrosoft by remember { mutableStateOf(64329.0) }
    var capitalofIBM by remember { mutableStateOf(497864.0) }
    var capitalofGoogle by remember { mutableStateOf(965398.0) }
    var capitalofMicrosystem by remember { mutableStateOf(265098.0) }
    var capitalofNokia by remember { mutableStateOf(348673.0) }
    var capitalofChinaOil by remember { mutableStateOf(547298.0) }
    var capitalofBlackRock by remember { mutableStateOf(512976.0) }
    var capitalofWarpTech by remember { mutableStateOf(286504.0) }
    var capitalofMitsumi by remember { mutableStateOf(265981.0) }
    var capitalofSamsung by remember { mutableStateOf(628465.0) }
    var capitalofNordIce by remember { mutableStateOf(244977.0) }
    var capitalofCocaCola by remember { mutableStateOf(176409.0) }

    var buyMicrosoft by remember { mutableStateOf(capitalofMicrosoft/500) }
    var buyIBM by remember { mutableStateOf(capitalofIBM/500) }
    var buyGoogle by remember { mutableStateOf(capitalofGoogle/500) }
    var buyMicrosystem by remember { mutableStateOf(capitalofMicrosystem/500) }
    var buyNokia by remember { mutableStateOf(capitalofNokia/500) }
    var buyChinaOil by remember { mutableStateOf(capitalofChinaOil/500) }
    var buyBlackRock by remember { mutableStateOf(capitalofBlackRock/500) }
    var buyWarpTech by remember { mutableStateOf(capitalofWarpTech/500) }
    var buyMitsumi by remember { mutableStateOf(capitalofMitsumi/500) }
    var buySamsung by remember { mutableStateOf(capitalofSamsung/500) }
    var buyNordIce by remember { mutableStateOf(capitalofNordIce/500) }
    var buyCocaCola by remember { mutableStateOf(capitalofCocaCola/500) }

    var saleMicrosoft by remember { mutableStateOf(capitalofMicrosoft/800) }
    var saleIBM by remember { mutableStateOf(capitalofIBM/800) }
    var saleGoogle by remember { mutableStateOf(capitalofGoogle/800) }
    var saleMicrosystem by remember { mutableStateOf(capitalofMicrosystem/800) }
    var saleNokia by remember { mutableStateOf(capitalofNokia/800) }
    var saleChinaOil by remember { mutableStateOf(capitalofChinaOil/800) }
    var saleBlackRock by remember { mutableStateOf(capitalofBlackRock/800) }
    var saleWarpTech by remember { mutableStateOf(capitalofWarpTech/800) }
    var saleMitsumi by remember { mutableStateOf(capitalofMitsumi/800) }
    var saleSamsung by remember { mutableStateOf(capitalofSamsung/800) }
    var saleNordIce by remember { mutableStateOf(capitalofNordIce/800) }
    var saleCocaCola by remember { mutableStateOf(capitalofCocaCola/800) }

    var stockMicrosoft by remember { mutableStateOf(0) }
    var stockIBM by remember { mutableStateOf(0) }
    var stockGoogle by remember { mutableStateOf(0) }
    var stockMicrosystem by remember { mutableStateOf(0) }
    var stockNokia by remember { mutableStateOf(0) }
    var stockChinaOil by remember { mutableStateOf(0) }
    var stockBlackRock by remember { mutableStateOf(0) }
    var stockWarpTech by remember { mutableStateOf(0) }
    var stockMitsumi by remember { mutableStateOf(0) }
    var stockSamsung by remember { mutableStateOf(0) }
    var stockNordIce by remember { mutableStateOf(0) }
    var stockCocaCola by remember { mutableStateOf(0) }

    var profitinmonth by remember { mutableStateOf(0.0) }
    var lossinmonth by remember { mutableStateOf(0.0) }
    var cash by remember { mutableStateOf(5000.0 + (profitinmonth - lossinmonth)) }
    var housing by remember { mutableStateOf(0.0) }
    var capital by remember { mutableStateOf(cash + housing) }
    var employees by remember { mutableStateOf(0) }

    var isDoctor by remember { mutableStateOf(false) }
    var isLawer by remember { mutableStateOf(false) }
    var isSecurity by remember { mutableStateOf(false) }

    var isMoto by remember { mutableStateOf(false) }
    var isAuto by remember { mutableStateOf(false) }
    var isApartment by remember { mutableStateOf(false) }
    var isWilla by remember { mutableStateOf(false) }
    var isFly by remember { mutableStateOf(false) }
    var isCastle by remember { mutableStateOf(false) }
    var isIsland by remember { mutableStateOf(false) }

    var isBar by remember { mutableStateOf(false) }
    var isDiner by remember { mutableStateOf(false) }
    var isHouse by remember { mutableStateOf(false) }
    var isHotel3 by remember { mutableStateOf(false) }
    var isHotel4 by remember { mutableStateOf(false) }
    var isHotel5 by remember { mutableStateOf(false) }
    var isAirport by remember { mutableStateOf(false) }
    var isFactory by remember { mutableStateOf(false) }
    var isManufacturing by remember { mutableStateOf(false) }

    val animationDuration = 300
    val enterTransition = remember {
         fadeIn(animationSpec = tween(animationDuration)) + expandVertically(animationSpec = tween(animationDuration), expandFrom = Alignment.Top)
    }
    val exitTransition = remember {
        fadeOut(animationSpec = tween(animationDuration)) + shrinkVertically(animationSpec = tween(animationDuration), shrinkTowards = Alignment.Top)
    }
     BackHandler {
        context.finishAffinity()
    }

    var selectedImage by remember { mutableStateOf<Uri?>(prefs.getString("selectedImageUri", null)?.toUri()) }
    val showDialogName = remember { mutableStateOf(false) }
    val showDialogAge = remember { mutableStateOf(false) }
    val showDialogEnd = remember { mutableStateOf(false) }
    val showDialogSuccess = remember { mutableStateOf(false) }
    var isAgeSet by remember { mutableStateOf(prefs.getBoolean("isAgeSet", false)) }
    var age by remember { mutableStateOf(prefs.getInt("age", 18)) }
    val startingAge by remember { mutableStateOf(prefs.getInt("starting_age", age)) }
    var currentMonth by remember { mutableStateOf(prefs.getInt("currentMonth", 1)) }
    var name by remember { mutableStateOf(prefs.getString("name", "Игрок") ?: "Игрок") }
    var health by remember { mutableStateOf(5) }
    var healthsecurity by remember { mutableStateOf("- Вы под защитой, Вам ничего не угрожает!") }
    var society by remember { mutableStateOf(0) }
    fun getAgeString(age: Int): String {
        val lastDigit = age % 10
        val lastTwoDigits = age % 100

        if (lastTwoDigits in 11..19) {
            return "лет"
        }
        return when (lastDigit) {
            1 -> "год"
            in 2..4 -> "года"
            else -> "лет"
        }
    }
    LaunchedEffect(key1 = isAgeSet) {
        if (!isAgeSet) {
            showDialogAge.value = true
        }
    }
    LaunchedEffect(Unit) {
    if (!prefs.contains("starting_age")) {
        prefs.edit {
            putInt("starting_age", age)
        }
    }
}

    val allPossibleEvents = remember {    listOf(
        listOf(
            "- Microsoft объявляет о рекордной прибыли, акции взлетели на 30%",
            "- Антимонопольный иск против Microsoft, акции упали на 40%"
        ),
        listOf(
            "- IBM заключила крупный госконтракт на облачные вычисления, акции +25%",
            "- IBM провалила квартальный отчет, акции -35%"
        ),
        listOf(
            "- Новый ИИ от Google превзошел все ожидания, акции +40%",
            "- Утечка данных в Google привела к массовым штрафам, акции -50%"
        ),
        listOf(
            "- Microsystem разработала революционный чип, акции +60%",
            "- Пожар на заводе Microsystem остановил производство, акции -70%"
        ),
        listOf(
            "- Nokia выиграла патентный спор, акции +50%",
            "- Nokia теряет долю на рынке телеком-оборудования, акции -45%"
        ),
        listOf(
            "- ChinaOil обнаружила новое гигантское месторождение нефти, акции +80%",
            "- Разлив нефти с танкера ChinaOil грозит многомиллиардными исками, акции -90%"
        ),
        listOf(
            "- BlackRock удачно инвестировал в стартап, получив сверхприбыль. Акции +35%",
            "- Неудачная инвестиционная стратегия BlackRock привела к убыткам. Акции -30%"
        ),
        listOf(
            "- WarpTech получила одобрение FDA на новое лекарство, акции взлетели на 150%!",
            "- Клинические испытания WarpTech провалились, акции рухнули на 80%"
        ),
        listOf(
            "- Игровая консоль с компонентами Mitsumi стала хитом продаж, акции +40%",
            "- Mitsumi отзывает бракованную партию электроники, акции -55%"
        ),
        listOf(
            "- Новый флагманский смартфон Samsung бьет рекорды продаж, акции +30%",
            "- Проблемы с аккумуляторами в устройствах Samsung, акции -40%"
        ),
        listOf(
            "- NordIce выходит на новый азиатский рынок, акции +25%",
            "- Скандал с качеством продукции NordIce, акции -60%"
        ),
        listOf(
            "- Coca-Cola отчиталась о рекордных продажах в летний сезон, акции +20%",
            "- Новый налог на сладкие напитки ударил по Coca-Cola, акции -25%"
        ),
        listOf(
            "- Инфляция замедлилась, центральный банк понижает ставку. Все рынки растут!",
            "- Данные по безработице хуже ожиданий, на рынках паника"
        ),
        listOf(
            "- Новое торговое соглашение между США и Китаем стимулирует рынки",
            "- Политический кризис в Европе пугает инвесторов"
        ),
        listOf(
            "- Прорыв в квантовых вычислениях толкает вверх весь технологический сектор",
            "- Глобальный дефицит полупроводников ударил по производителям электроники"
        ),
        listOf(
            "- Нобелевская премия в области медицины присуждена биотех-компании",
            "- Крупный танкер заблокировал Суэцкий канал, логистика по всему миру нарушена"
        ),
        listOf(
            "- Рекордный урожай кофе в Бразилии обрушил цены на сырье",
            "- Открытие нового способа добычи лития удешевляет производство батарей"
        ),
        listOf(
            "- Ваш офис ограбили! Вы потеряли значительную часть наличных  -2000",
            "- Вы выиграли в лотерею крупную сумму денег!  +5000"
        ),
        listOf(
            "- Вы стали жертвой финансовой пирамиды, потеряв часть капитала  -1000",
            "- Дальний родственник оставил вам неожиданное наследство  +3000"
        ),
        listOf(
            "- Вы получили престижную премию 'Предприниматель года'!  +500",
            "- Ваша компания оказалась в центре небольшого скандала, репутация под угрозой.  -1000"
        ),
        listOf(
            "- Совершена революция в области термоядерного синтеза! Энергетические компании в панике.",
            "- Искусственный интеллект научился писать музыку, акции звукозаписывающих компаний падают."
        ),
        listOf(
            "- Вас побили на улице, здоровье ухудшилось -2",
           "- Вы успешно прошли курс оздоровительных процедур, здоровье улучшилось +1"
        ),
        listOf(
            "- Вас пытаются переманить конкуренты, предлагая высокую должность.  +1000",
            "- Вы уволили некомпетентного сотрудника, производительность выросла, но настроение в коллективе упало.  -1000"
        ),
        listOf(
            "- Ваш личный врач скончался!"
        ),
        listOf(
            "- Ваша охрана бросила Вас, т.к. Вы не захотели платить больше!"
        )
    ) }
    var selectedEvents by remember { mutableStateOf<List<String>>(emptyList()) }

    val progress = currentMonth / 12f

    // Пример кода для кнопки "Новая игра"
//prefs.edit {
//    putBoolean("isAgeSet", false)
//    // тут же можно сбросить и другие данные, например, имя
//    // putString("name", "Игрок")
//}
// После этого можно перезапустить экран или навигироваться на него заново


     Box(modifier = Modifier.fillMaxSize().systemBarsPadding(), contentAlignment = Alignment.Center) {
         Image(painter = painterResource(R.drawable.fonpleten), contentDescription = "fon",
             modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds
         )
         Column(modifier = Modifier.fillMaxSize().systemBarsPadding().verticalScroll(rememberScrollState()),
             horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
             Spacer(modifier = Modifier.height(8.dp))
             Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically,
                 horizontalArrangement = Arrangement.SpaceBetween){
                 Column(horizontalAlignment = Alignment.CenterHorizontally){
                      Text(text = "Ваше состояние", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny))
                     )
                 Row(verticalAlignment = Alignment.CenterVertically){
                     Image(painter = painterResource(R.drawable.money), contentDescription = "money",
                          modifier = Modifier.width(50.dp).height(30.dp), contentScale = ContentScale.FillBounds
                     )
                     Spacer(modifier = Modifier.width(16.dp))
                     Text(text = "${capital.roundToInt()}", fontSize = 20.sp, color = colorResource(R.color.green),
                         fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny))
                     )
                 }
                 }
                 Row(verticalAlignment = Alignment.CenterVertically){
                     val ageInt = age
                     val ageSuffix = getAgeString(ageInt)
                     Text(text = "$age $ageSuffix", fontSize = 16.sp, color = colorResource(R.color.green),
                         fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                          modifier = Modifier.clickable(enabled = !isAgeSet){ showDialogAge.value = true }
                     )
                     if (showDialogAge.value) {
                         AlertDialog(
                             onDismissRequest = { if (isAgeSet) { showDialogAge.value = false } },
                             containerColor = colorResource(id = R.color.white),
                             text = {
                                 Column(modifier = Modifier.fillMaxWidth().height(200.dp),
                                     horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly){
                                     Text(text = "Сколько Вам лет?", fontSize = 24.sp, color = colorResource(R.color.green),
                                         fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                                     )
                                     OutlinedTextField(value = "$age", onValueChange = { newValue -> age = newValue.toInt() },
                                         placeholder = { Text("Введите свой возраст", color = colorResource(R.color.green)) },
                                         textStyle = TextStyle(color = colorResource(R.color.green), fontSize = 20.sp),
                                         modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).background(
                                             colorResource(R.color.white), RoundedCornerShape(8.dp)),
                                         colors = TextFieldDefaults.outlinedTextFieldColors(
                                             focusedBorderColor = colorResource(R.color.green),
                                             unfocusedBorderColor = colorResource(R.color.green),
                                             cursorColor = colorResource(R.color.green),
                                             placeholderColor = colorResource(R.color.green),
                                             textColor = colorResource(R.color.green)
                                         ),
                                         shape = RoundedCornerShape(8.dp)
                                     )
                                     Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                         verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
                                         Image(painter = painterResource(R.drawable.butcancel), contentDescription = "butcancel",
                                              modifier = Modifier.width(130.dp).height(50.dp).clickable{
                                                 if (isAgeSet) {
                                                      showDialogAge.value = false
                                                  }
                                              }, contentScale = ContentScale.FillBounds
                                         )
                                         Image(painter = painterResource(R.drawable.butyes), contentDescription = "butyes",
                                              modifier = Modifier.width(70.dp).height(50.dp).clickable{
                                                  prefs.edit {
                                                      putInt("age", age)
                                                      putBoolean("isAgeSet", true)
                                                  }
                                                  isAgeSet = true
                                                  showDialogAge.value = false
                                              }, contentScale = ContentScale.FillBounds
                                         )
                                     }
                                 }
                             },
                             confirmButton = {},
                             dismissButton = {})
                     }
                     Column(horizontalAlignment = Alignment.CenterHorizontally) {
                         PlayerPhotoSectionResult(
                             initialImageUri = selectedImage, onImageSelected = {
                                 selectedImage = it
                                 prefs.edit { putString("selectedImageUri", it.toString()) }
                             })
                         Spacer(modifier = Modifier.height(8.dp))
                         Text(text = name, fontSize = 16.sp, color = colorResource(R.color.green),
                             fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                             modifier = Modifier.clickable{ showDialogName.value = true }, textAlign = TextAlign.Center
                         )
                         if (showDialogName.value) {
                             AlertDialog(
                                 onDismissRequest = { showDialogName.value = false },
                                 containerColor = colorResource(id = R.color.white),
                                 text = {
                                   Column(modifier = Modifier.fillMaxWidth().height(200.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly){
                                     Text(text = "Ваше имя?", fontSize = 24.sp, color = colorResource(R.color.green),
                                         fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                                     )
                                     OutlinedTextField(value = name, onValueChange = { newValue -> name = newValue },
                                         placeholder = { Text("Введите свое имя", color = colorResource(R.color.green)) },
                                         textStyle = TextStyle(color = colorResource(R.color.green), fontSize = 20.sp),
                                         modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).background(
                                             colorResource(R.color.white), RoundedCornerShape(8.dp)),
                                         colors = TextFieldDefaults.outlinedTextFieldColors(
                                             focusedBorderColor = colorResource(R.color.green),
                                             unfocusedBorderColor = colorResource(R.color.green),
                                             cursorColor = colorResource(R.color.green),
                                             placeholderColor = colorResource(R.color.green),
                                             textColor = colorResource(R.color.green)
                                         ),
                                         shape = RoundedCornerShape(8.dp)
                                     )
                                     Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                         verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
                                         Image(painter = painterResource(R.drawable.butcancel), contentDescription = "butcancel",
                                              modifier = Modifier.width(130.dp).height(50.dp).clickable{
                                                showDialogName.value = false
                                              }, contentScale = ContentScale.FillBounds
                                         )
                                         Image(painter = painterResource(R.drawable.butyes), contentDescription = "butyes",
                                              modifier = Modifier.width(70.dp).height(50.dp).clickable{
                                                  prefs.edit { putString("name", name) }
                                                showDialogName.value = false
                                              }, contentScale = ContentScale.FillBounds
                                         )
                                     }
                                 }
                                 },
                                 confirmButton = {},
                                 dismissButton = {})
                         }
                     }
                 }
             }
             Spacer(modifier = Modifier.height(8.dp))
             Box(modifier = Modifier.padding(horizontal = 8.dp)) {
                 LinearProgressIndicator(
                     progress = { progress },
                     modifier = Modifier.fillMaxWidth().height(8.dp),
                     color = colorResource(R.color.red),
                     trackColor = colorResource(R.color.trgray)
                 )
             }
             Spacer(modifier = Modifier.height(8.dp))
             Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                 .border(1.dp, color = colorResource(R.color.green), shape = RoundedCornerShape(8.dp))
                 .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.trgreen))),
                         RoundedCornerShape(8.dp))
                 .padding(8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
                 Column(modifier = Modifier.height(90.dp), horizontalAlignment = Alignment.Start,
                     verticalArrangement = Arrangement.SpaceEvenly){
                     Text(text = "Наличные ${cash.roundToInt()}", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                     Text(text = "Недвижимость ${housing.roundToInt()}", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                      Text(text = "Доход в месяц ${profitinmonth.roundToInt()}", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                     Text(text = "Расход в месяц ${lossinmonth.roundToInt()}", fontSize = 14.sp, color = colorResource(R.color.green),
                         fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                      Text(text = "Рабочих $employees", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                 }
                  Column(modifier = Modifier.height(90.dp), horizontalAlignment = Alignment.Start,
                     verticalArrangement = Arrangement.Top){
                      Spacer(modifier = Modifier.height(4.dp))
                     Text(text = "В банке 0", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                      Spacer(modifier = Modifier.height(8.dp))
                     Text(text = "Проценты 5%г 0", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                      Spacer(modifier = Modifier.height(6.dp))
                     Text(text = "Кредит 2%м 0", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                      Spacer(modifier = Modifier.height(8.dp))
                      Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable{
                          isBank = !isBank
                      }){
                          Image(painter = painterResource(R.drawable.iconbank), contentDescription = "iconbank",
                               modifier = Modifier.size(30.dp), contentScale = ContentScale.FillBounds
                          )
                          Spacer(modifier = Modifier.width(8.dp))
                          Text(text = if(isBank) "Закрыть" else "Открыть", fontSize = 14.sp, color = colorResource(R.color.blue),
                              fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                          )
                      }
                 }
             }
             Spacer(modifier = Modifier.height(4.dp))
             AnimatedVisibility(visible = isBank,  enter = enterTransition, exit = exitTransition ) {
                 Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                 .border(1.dp, color = colorResource(R.color.green), shape = RoundedCornerShape(8.dp))
                 .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.trgreen))),
                         RoundedCornerShape(8.dp))
                 .padding(8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
                 Column(modifier = Modifier.height(104.dp), horizontalAlignment = Alignment.Start,
                     verticalArrangement = Arrangement.SpaceEvenly){
                     Text(text = "Взять ссуду", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                      ItemBank("100", {}, {})
                      ItemBank("1000", {}, {})
                      ItemBank("10000", {}, {})
                      ItemBank("100000", {}, {})
                 }
                  Column(modifier = Modifier.height(104.dp), horizontalAlignment = Alignment.Start,
                     verticalArrangement = Arrangement.SpaceEvenly){
                      Text(text = "Сделать вклад", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                      )
                      ItemBank("100", {}, {})
                      ItemBank("1000", {}, {})
                      ItemBank("10000", {}, {})
                      ItemBank("100000", {}, {})
                 }
             }
             }
             Spacer(modifier = Modifier.height(4.dp))
              Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                 .border(1.dp, color = colorResource(R.color.blue), shape = RoundedCornerShape(8.dp))
                 .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.trblue))), RoundedCornerShape(8.dp))
                 .padding(8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
                  Column(modifier = Modifier.height(150.dp).width(140.dp), horizontalAlignment = Alignment.Start,
                     verticalArrangement = Arrangement.SpaceEvenly){
                      ItemBirga("Microsoft", perMicrosoft, isMicrosoft, firstMicrosoft)
                      ItemBirga("IBM", perIBM, isIBM, firstIBM)
                      ItemBirga("Google", perGoogle, isGoogle, firstGoogle)
                      ItemBirga("Microsystem", perMicrosystem, isMicrosystem, firstMicrosystem)
                      ItemBirga("Nokia", perNokia, isNokia, firstNokia)
                      ItemBirga("China Oil", perChinaOil, isChinaOil, firstChinaOil)
                  }
                  Column(modifier = Modifier.height(150.dp).width(140.dp), horizontalAlignment = Alignment.Start,
                     verticalArrangement = Arrangement.SpaceEvenly){
                      ItemBirga("Black Rock", perBlackRock, isBlackRock, firstBlackRock)
                      ItemBirga("Warp Tech", perWarpTech, isWarpTech, firstWarpTech)
                      ItemBirga("Mitsumi", perMitsumi, isMitsumi, firstMitsumi)
                      ItemBirga("Samsung", perSamsung, isSamsung, firstSamsung)
                      ItemBirga("Nord Ice", perNordIce, isNordIce, firstNordIce)
                      ItemBirga("Coca Cola" , perCocaCola, isCocaCola, firstCocaCola)
                  }
                  Box(modifier = Modifier.width(30.dp).height(150.dp)
                      .border(1.dp, color = colorResource(R.color.gray), shape = RoundedCornerShape(8.dp))
                      .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.trorange))),
                         RoundedCornerShape(8.dp)).clickable{ isBirga = !isBirga }, contentAlignment = Alignment.Center){
                       Text(text = if(isBirga) "З\nа\nк\nр\nы\nт\nь" else "П\nо\nд\nр\nо\nб\nн\nе\nе", fontSize = 14.sp, color = colorResource(R.color.blue),
                          fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                  }
              }
             Spacer(modifier = Modifier.height(4.dp))
              AnimatedVisibility(visible = isBirga,  enter = enterTransition, exit = exitTransition ) {
                      Row(modifier = Modifier.fillMaxWidth().height(340.dp).padding(horizontal = 8.dp)
                      .border(1.dp, color = colorResource(R.color.blue), shape = RoundedCornerShape(8.dp))
                      .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.trblue))),
                         RoundedCornerShape(8.dp)).padding(4.dp),
                          verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                          Column(modifier = Modifier.fillMaxHeight().width(120.dp),
                              horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                              Spacer(modifier = Modifier.height(4.dp))
                              Text(text = "Компания", fontSize = 15.sp, color = colorResource(R.color.orange),
                                  fontFamily = FontFamily(Font(R.font.coiny)),
                              )
                              Spacer(modifier = Modifier.height(20.dp))
                              NameCompany("Microsoft", profitMicrosoft, {
                                  if (stockMicrosoft < 100) {
                                      val cost = kotlin.math.abs(buyMicrosoft)
                                      if (cash >= cost) {
                                          stockMicrosoft++
                                          capital -= cost
                                          cash -= cost
                                         val perc10 = (saleMicrosoft * 10) / 100
                                          profitMicrosoft += perc10
                                           capital -= perc10
                                          cash -= perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockMicrosoft > 0) {
                                      stockMicrosoft--
                                      capital += saleMicrosoft
                                      cash += saleMicrosoft
                                      val perc10 = (saleMicrosoft * 10) / 100
                                      profitMicrosoft -= perc10
                                      profitinmonth -= perc10
                                  } else {
                                      profitMicrosoft = 0.0
                                      Toast.makeText(context, "У вас нет акций!", Toast.LENGTH_SHORT).show()
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("IBM", profitIBM, {
                                  if (stockIBM < 100) {
                                      val cost = kotlin.math.abs(buyIBM)
                                      if (cash >= cost) {
                                          stockIBM++
                                          capital -= cost
                                          cash -= cost
                                         val perc10 = (saleIBM * 10) / 100
                                          profitIBM += perc10
                                          capital -= perc10
                                          cash -= perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockIBM > 0) {
                                      stockIBM--
                                       capital += saleIBM
                                      cash += saleIBM
                                      val perc10 = (saleIBM * 10) / 100
                                      profitIBM -= perc10
                                      profitinmonth -= perc10
                                  }
                                  else {
                                      profitIBM = 0.0
                                      Toast.makeText(context, "У вас нет акций!", Toast.LENGTH_SHORT).show()
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Google", profitGoogle, {
                                  if (stockGoogle < 100) {
                                      val cost = kotlin.math.abs(buyGoogle)
                                      if (cash >= cost) {
                                          stockGoogle++
                                          capital -= cost
                                          cash -= cost
                                         val perc10 = (saleGoogle * 10) / 100
                                          profitGoogle += perc10
                                          capital -= perc10
                                          cash -= perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockGoogle > 0) {
                                      stockGoogle--
                                       capital += saleGoogle
                                      cash += saleGoogle
                                      val perc10 = (saleGoogle * 10) / 100
                                      profitGoogle -= perc10
                                      profitinmonth -= perc10
                                  }
                                  else {
                                      profitGoogle = 0.0
                                      Toast.makeText(context, "У вас нет акций!", Toast.LENGTH_SHORT).show()
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Microsystem", profitMicrosystem,{
                                 if (stockMicrosystem < 100) {
                                      val cost = kotlin.math.abs(buyMicrosystem)
                                      if (cash >= cost) {
                                          stockMicrosystem++
                                          capital -= cost
                                          cash -= cost
                                         val perc10 = (saleMicrosystem * 10) / 100
                                          profitMicrosystem += perc10
                                          capital -= perc10
                                          cash -= perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockMicrosystem > 0) {
                                      stockMicrosystem--
                                       capital += saleMicrosystem
                                      cash += saleMicrosystem
                                      val perc10 = (saleMicrosystem * 10) / 100
                                      profitMicrosystem -= perc10
                                      profitinmonth -= perc10
                                  }
                                  else {
                                      profitMicrosystem = 0.0
                                      Toast.makeText(context, "У вас нет акций!", Toast.LENGTH_SHORT).show()
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Nokia", profitNokia, {
                                 if (stockNokia < 100) {
                                      val cost = kotlin.math.abs(buyNokia)
                                      if (cash >= cost) {
                                          stockNokia++
                                          capital -= cost
                                          cash -= cost
                                         val perc10 = (saleNokia * 10) / 100
                                          profitNokia += perc10
                                          capital -= perc10
                                          cash -= perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockNokia > 0) {
                                      stockNokia--
                                       capital += saleNokia
                                      cash += saleNokia
                                      val perc10 = (saleNokia * 10) / 100
                                      profitNokia -= perc10
                                      profitinmonth -= perc10
                                  }
                                  else {
                                      profitNokia = 0.0
                                      Toast.makeText(context, "У вас нет акций!", Toast.LENGTH_SHORT).show()
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("China Oil", profitChinaOil,{
                                 if (stockChinaOil < 100) {
                                      val cost = kotlin.math.abs(buyChinaOil)
                                      if (cash >= cost) {
                                          stockChinaOil++
                                          capital -= cost
                                          cash -= cost
                                         val perc10 = (saleChinaOil * 10) / 100
                                          profitChinaOil += perc10
                                          capital -= perc10
                                          cash -= perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockChinaOil > 0) {
                                      stockChinaOil--
                                       capital += saleChinaOil
                                      cash += saleChinaOil
                                      val perc10 = (saleChinaOil * 10) / 100
                                      profitChinaOil -= perc10
                                      profitinmonth -= perc10
                                  }
                                  else {
                                      profitChinaOil = 0.0
                                      Toast.makeText(context, "У вас нет акций!", Toast.LENGTH_SHORT).show()
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Black Rock", profitBlackRock,{
                                if (stockBlackRock < 100) {
                                      val cost = kotlin.math.abs(buyBlackRock)
                                      if (cash >= cost) {
                                          stockBlackRock++
                                          capital -= cost
                                          cash -= cost
                                         val perc10 = (saleBlackRock * 10) / 100
                                          profitBlackRock += perc10
                                          capital -= perc10
                                          cash -= perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockBlackRock > 0) {
                                      stockBlackRock--
                                       capital += saleBlackRock
                                      cash += saleBlackRock
                                      val perc10 = (saleBlackRock * 10) / 100
                                      profitBlackRock -= perc10
                                      profitinmonth -= perc10
                                  }
                                  else {
                                      profitBlackRock = 0.0
                                      Toast.makeText(context, "У вас нет акций!", Toast.LENGTH_SHORT).show()
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Warp Tech", profitWarpTech,{
                                 if (stockWarpTech < 100) {
                                      val cost = kotlin.math.abs(buyWarpTech)
                                      if (cash >= cost) {
                                          stockWarpTech++
                                          capital -= cost
                                          cash -= cost
                                         val perc10 = (saleWarpTech * 10) / 100
                                          profitWarpTech += perc10
                                          capital -= perc10
                                          cash -= perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockWarpTech > 0) {
                                      stockWarpTech--
                                       capital += saleWarpTech
                                      cash += saleWarpTech
                                      val perc10 = (saleWarpTech * 10) / 100
                                      profitWarpTech -= perc10
                                      profitinmonth -= perc10
                                  }
                                  else {
                                      profitWarpTech = 0.0
                                      Toast.makeText(context, "У вас нет акций!", Toast.LENGTH_SHORT).show()
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Mitsumi", profitMitsumi,{
                                 if (stockMitsumi < 100) {
                                      val cost = kotlin.math.abs(buyMitsumi)
                                      if (cash >= cost) {
                                           stockMitsumi++
                                          capital -= cost
                                          cash -= cost
                                         val perc10 = (saleMitsumi * 10) / 100
                                          profitMitsumi += perc10
                                          capital -= perc10
                                          cash -= perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockMitsumi > 0) {
                                      stockMitsumi--
                                       capital += saleMitsumi
                                      cash += saleMitsumi
                                      val perc10 = (saleMitsumi * 10) / 100
                                      profitMitsumi -= perc10
                                      profitinmonth -= perc10
                                  }
                                  else {
                                      profitMitsumi = 0.0
                                      Toast.makeText(context, "У вас нет акций!", Toast.LENGTH_SHORT).show()
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Samsung", profitSamsung,{
                                 if (stockSamsung < 100) {
                                      val cost = kotlin.math.abs(buySamsung)
                                      if (cash >= cost) {
                                           stockSamsung++
                                          capital -= cost
                                          cash -= cost
                                         val perc10 = (saleSamsung * 10) / 100
                                          profitSamsung += perc10
                                          capital -= perc10
                                          cash -= perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockSamsung > 0) {
                                      stockSamsung--
                                       capital += saleSamsung
                                      cash += saleSamsung
                                      val perc10 = (saleSamsung * 10) / 100
                                      profitSamsung -= perc10
                                      profitinmonth -= perc10
                                  }
                                  else {
                                      profitSamsung = 0.0
                                      Toast.makeText(context, "У вас нет акций!", Toast.LENGTH_SHORT).show()
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Nord Ice", profitNordIce,{
                                  if (stockNordIce < 100) {
                                      val cost = kotlin.math.abs(buyNordIce)
                                      if (cash >= cost) {
                                           stockNordIce++
                                          capital -= cost
                                          cash -= cost
                                         val perc10 = (saleNordIce * 10) / 100
                                          profitNordIce += perc10
                                          capital -= perc10
                                          cash -= perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockNordIce > 0) {
                                      stockNordIce--
                                       capital += saleNordIce
                                      cash += saleNordIce
                                      val perc10 = (saleNordIce * 10) / 100
                                      profitNordIce -= perc10
                                      profitinmonth -= perc10
                                  }
                                  else {
                                      profitNordIce = 0.0
                                      Toast.makeText(context, "У вас нет акций!", Toast.LENGTH_SHORT).show()
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Coca Cola", profitCocaCola,{
                                  if (stockCocaCola < 100) {
                                      val cost = kotlin.math.abs(buyCocaCola)
                                      if (cash >= cost) {
                                           stockCocaCola++
                                          capital -= cost
                                          cash -= cost
                                         val perc10 = (saleCocaCola * 10) / 100
                                          profitCocaCola += perc10
                                          capital -= perc10
                                          cash -= perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockCocaCola > 0) {
                                      stockCocaCola--
                                       capital += saleCocaCola
                                      cash += saleCocaCola
                                      val perc10 = (saleCocaCola * 10) / 100
                                      profitCocaCola -= perc10
                                      profitinmonth -= perc10
                                  }
                                  else {
                                      profitCocaCola = 0.0
                                      Toast.makeText(context, "У вас нет акций!", Toast.LENGTH_SHORT).show()
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                          }
                          Column(modifier = Modifier.fillMaxHeight().weight(1f),
                              horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                              Text(text = "доход\n в месяц", fontSize = 12.sp, color = colorResource(R.color.black),
                                  fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                              )
                              Spacer(modifier = Modifier.height(12.dp))
                              Coins(profitMicrosoft)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(profitIBM)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(profitGoogle)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(profitMicrosystem)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(profitNokia)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(profitChinaOil)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(profitBlackRock)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(profitWarpTech)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(profitMitsumi)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(profitSamsung)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(profitNordIce)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(profitCocaCola)
                          }
                           Column(modifier = Modifier.fillMaxHeight().weight(1f),
                               horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                               Spacer(modifier = Modifier.height(4.dp))
                               Text(text = "купля", fontSize = 12.sp, color = colorResource(R.color.black),
                                   fontFamily = FontFamily(Font(R.font.coiny)),
                               )
                              Spacer(modifier = Modifier.height(23.dp))
                              Coins(buyMicrosoft)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(buyIBM)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(buyGoogle)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(buyMicrosystem)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(buyNokia)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(buyChinaOil)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(buyBlackRock)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(buyWarpTech)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(buyMitsumi)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(buySamsung)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(buyNordIce)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(buyCocaCola)
                              Spacer(modifier = Modifier.height(11.dp))
                          }
                           Column(modifier = Modifier.fillMaxHeight().weight(1f),
                               horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                               Spacer(modifier = Modifier.height(4.dp))
                               Text(text = "продажа", fontSize = 12.sp, color = colorResource(R.color.black),
                                   fontFamily = FontFamily(Font(R.font.coiny)),
                               )
                              Spacer(modifier = Modifier.height(23.dp))
                              Coins(saleMicrosoft)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(saleIBM)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(saleGoogle)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(saleMicrosystem)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(saleNokia)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(saleChinaOil)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(saleBlackRock)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(saleWarpTech)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(saleMitsumi)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(saleSamsung)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(saleNordIce)
                              Spacer(modifier = Modifier.height(11.dp))
                              Coins(saleCocaCola)
                              Spacer(modifier = Modifier.height(11.dp))
                          }
                           Column(modifier = Modifier.fillMaxHeight().width(36.dp),
                               horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                               Spacer(modifier = Modifier.height(4.dp))
                               Text(text = "%\nакций", fontSize = 12.sp, color = colorResource(R.color.black),
                                   fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                               )
                              Spacer(modifier = Modifier.height(12.dp))
                              CoinsPercents(stockMicrosoft, buyMicrosoft)
                              Spacer(modifier = Modifier.height(11.dp))
                              CoinsPercents(stockIBM, buyIBM)
                              Spacer(modifier = Modifier.height(11.dp))
                              CoinsPercents(stockGoogle, buyGoogle)
                              Spacer(modifier = Modifier.height(11.dp))
                              CoinsPercents(stockMicrosystem, buyMicrosystem)
                              Spacer(modifier = Modifier.height(11.dp))
                              CoinsPercents(stockNokia, buyNokia)
                              Spacer(modifier = Modifier.height(11.dp))
                              CoinsPercents(stockChinaOil, buyChinaOil)
                              Spacer(modifier = Modifier.height(11.dp))
                              CoinsPercents(stockBlackRock, buyBlackRock)
                              Spacer(modifier = Modifier.height(11.dp))
                              CoinsPercents(stockWarpTech, buyWarpTech)
                              Spacer(modifier = Modifier.height(11.dp))
                              CoinsPercents(stockMitsumi, buyMitsumi)
                              Spacer(modifier = Modifier.height(11.dp))
                              CoinsPercents(stockSamsung, buySamsung)
                              Spacer(modifier = Modifier.height(11.dp))
                              CoinsPercents(stockNordIce, buyNordIce)
                              Spacer(modifier = Modifier.height(11.dp))
                              CoinsPercents(stockCocaCola, buyCocaCola)
                              Spacer(modifier = Modifier.height(11.dp))
                          }
                      }
              }
             Spacer(modifier = Modifier.height(4.dp))
              Row(modifier = Modifier.fillMaxWidth().height(60.dp).padding(horizontal = 8.dp)
                      .border(1.dp, color = colorResource(R.color.violet), shape = RoundedCornerShape(8.dp))
                      .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.trviolet))),
                         RoundedCornerShape(8.dp)).padding(4.dp),
                          verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                  Column(modifier = Modifier.height(60.dp),
                      horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceEvenly){
                      ItemEmployee("Рабочий", {if(employees > 0) employees--
                                              lossinmonth -= 4}, {
                          if (cash > 40) {
                              employees++
                              capital -= 40
                              cash -= 40
                              lossinmonth += 4
                          } else {
                              Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                          }
                      }
                      )
                      CheckItem("Врач", "160", isDoctor,{
                          if(!isDoctor){
                          if(cash > 160) {
                              capital -= 160
                              cash -= 160
                              isDoctor = true
                              lossinmonth += 16
                          } else {
                               Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                          }
                      } else {
                           Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                      }
                      })
                  }
                   Column(modifier = Modifier.height(60.dp),
                      horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.SpaceEvenly){
                       CheckItem("Адвокат", "344",  isLawer,{
                           if(!isLawer){
                           if(cash > 344 && !isLawer) {
                               capital -= 344
                               cash -= 344
                               isLawer = true
                               lossinmonth += 34
                           } else {
                               Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                           }
                           } else {
                                Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                           }
                       }
                      )
                       CheckItem("Охрана", "605", isSecurity,{
                           if(!isSecurity){
                           if(cash > 605 && !isSecurity) {
                               capital -= 605
                               cash -= 605
                               isSecurity = true
                               lossinmonth += 60
                           } else {
                                Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                           }
                           } else {
                                Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                           }
                       }
                      )
                   }
              }
             Spacer(modifier = Modifier.height(4.dp))
             AnimatedVisibility(visible = isLawer, enter = enterTransition, exit = exitTransition) {
                 LawerEmployeeActive("Подняться по служебной лестнице", {

                     if (society == 4 && isMoto && isAuto && isApartment && isWilla && isFly && isCastle && isIsland) {
                         if (age >= (startingAge + 25)) {
                             society = 5
                             showDialogSuccess.value = true
                         } else {
                             val yearsLeft = (startingAge + 25) - age
                             Toast.makeText(context, "У вас есть всё! Для высшего статуса осталось подождать: $yearsLeft лет", Toast.LENGTH_LONG).show()
                         }
                     } else if (society == 3 && isMoto && isAuto && isApartment && isWilla && isFly && isCastle) {
                         if (age >= (startingAge + 20)) {
                             society = 4
                             Toast.makeText(context, "Отлично! Теперь осталось купить остров!", Toast.LENGTH_LONG).show()
                         } else {
                             val yearsLeft = (startingAge + 20) - age
                             Toast.makeText(context, "У вас есть дворец! Для следующего уровня осталось подождать: $yearsLeft лет", Toast.LENGTH_LONG).show()
                         }
                     } else if (society == 2 &&isMoto && isAuto && isApartment && isWilla && isFly) {
                         if (age >= (startingAge + 15)) {
                             society = 3
                             Toast.makeText(context, "Прекрасно! Для следующего уровня нужен дворец!", Toast.LENGTH_LONG).show()
                         } else {
                             val yearsLeft = (startingAge + 15) - age
                             Toast.makeText(context, "У вас есть самолет! Для повышения осталось подождать: $yearsLeft лет", Toast.LENGTH_LONG).show()
                         }
                     } else if (society == 1 &&isMoto && isAuto && isApartment && isWilla) {
                         if (age >= (startingAge + 10)) {
                             society = 2
                             Toast.makeText(context, "Хорошая работа! Теперь нацельтесь на самолет!", Toast.LENGTH_LONG).show()
                         } else {
                             val yearsLeft = (startingAge + 10) - age
                             Toast.makeText(context, "У вас есть вилла! Для повышения осталось подождать: $yearsLeft лет", Toast.LENGTH_LONG).show()
                         }
                     } else if (society == 0 && isMoto && isAuto && isApartment) {
                         if (age >= (startingAge + 5)) {
                             society = 1
                             Toast.makeText(context, "Вы на верном пути! Следующая цель - вилла!", Toast.LENGTH_LONG).show()
                         } else {
                             val yearsLeft = (startingAge + 5) - age
                             Toast.makeText(context, "У вас есть квартира! Для первого статуса осталось подождать: $yearsLeft лет", Toast.LENGTH_LONG).show()
                         }
                     } else {
                         when (society) {
                             0 -> Toast.makeText(context, "Для первого шага нужно купить мотоцикл, машину и квартиру!", Toast.LENGTH_LONG).show()
                             1 -> Toast.makeText(context, "Сначала нужно купить виллу!", Toast.LENGTH_LONG).show()
                             2 -> Toast.makeText(context, "Ваша следующая цель - самолет!", Toast.LENGTH_LONG).show()
                             3 -> Toast.makeText(context, "Теперь нужно купить дворец!", Toast.LENGTH_LONG).show()
                             4 -> Toast.makeText(context, "Осталось последнее - купить остров!", Toast.LENGTH_LONG).show()
                             else -> Toast.makeText(context, "Вы уже достигли вершины!", Toast.LENGTH_LONG).show()
                         }
                     }
                 })
             }
             if (showDialogSuccess.value) {
                     AlertDialog(
                         onDismissRequest = { showDialogSuccess.value = false },
                         containerColor = colorResource(id = R.color.trred),
                         text = {
                              Column(modifier = Modifier.fillMaxWidth().height(150.dp).padding(horizontal = 16.dp),
                                 horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                                 Spacer(modifier = Modifier.height(8.dp))
                                 Text(text = "Вы достигли высоких показателей и стали настоящим Бизнесменом!",
                                     fontSize = 18.sp, color = colorResource(R.color.black),
                                     fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                                     textAlign = TextAlign.Center
                                 )
                                 Spacer(modifier = Modifier.height(16.dp))
                                  Box(modifier = Modifier.height(60.dp).width(200.dp).padding(horizontal = 4.dp)
                                           .border(2.dp, colorResource(R.color.red), RoundedCornerShape(8.dp))
                                           .background(Brush.horizontalGradient(listOf(colorResource(R.color.white),
                                               colorResource(R.color.red))), RoundedCornerShape(8.dp)).clickable {
                                               showDialogSuccess.value = false
                                                //
                                           }, contentAlignment = Alignment.Center) {
                                           Text(text = "Начать снова?", fontSize = 18.sp, color = colorResource(R.color.black),
                                               fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                                           )
                                       }
                                 Spacer(modifier = Modifier.height(16.dp))

                             }
                         },
                         confirmButton = {},
                         dismissButton = {})
                 }
             AnimatedVisibility(visible = isDoctor,  enter = enterTransition, exit = exitTransition ){
                  Column(){
                      Spacer(modifier = Modifier.height(4.dp))
                      LawerEmployeeActive("Экстренная госпитализация 10000", {
                          if(cash > 10000){
                              capital -= 10000
                              cash -= 10000
                              health += 1
                          } else {
                              Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                          }
                      })
                      Spacer(modifier = Modifier.height(4.dp))
                      LawerEmployeeActive("Консультация у врача", {
                         Toast.makeText(context, when (health) {
                             in -5..-4 -> "Вы при смерти! Срочно нужна госпитализация! Ваше здоровье -4"
                             -3 -> "Ваше состояние критическое. Любая простуда может стать последней. Ваше здоровье -3"
                             -2 -> "Серьезные проблемы со здоровьем. Вам нужен постоянный уход. Ваше здоровье -2"
                             -1 -> "Вы чувствуете себя очень плохо. Постоянная слабость и недомогание. Ваше здоровье -1"
                             0 -> "Вы на грани. Никакой энергии, иммунитет на нуле. Ваше здоровье 0"
                             1 -> "Вы чувствуете себя неважно. Стоит больше отдыхать. Ваше здоровье +1"
                             2 -> "Здоровье в порядке, но не стоит им пренебрегать. Ваше здоровье +2"
                             3 -> "Вы в хорошей форме! Продолжайте в том же духе. Ваше здоровье +3"
                             4 -> "Отличное самочувствие! Вы полны сил и энергии. Ваше здоровье +4"
                             5 -> "Вы в превосходной форме! Ваше здоровье - как у космонавта. Ваше здоровье +5"
                             else -> {
                                 if (health < -5) "Хуже некуда... Вы почти труп. Ваше здоровье -5"
                                 else "Вы абсолютно здоровы! Ваше здоровье 5+"
                             }
                         }, Toast.LENGTH_LONG).show()
                      })
                  }

             }
             Spacer(modifier = Modifier.height(4.dp))
             Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                 verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
                  Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable{
                          isHousing = !isHousing
                      }){
                          Image(painter = painterResource(R.drawable.housing), contentDescription = "housing",
                               modifier = Modifier.size(30.dp), contentScale = ContentScale.FillBounds
                          )
                          Spacer(modifier = Modifier.width(8.dp))
                          Text(text = if(isHousing) "Закрыть" else "Открыть", fontSize = 14.sp, color = colorResource(R.color.blue),
                              fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                          )
                      }
                  Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable{
                          isBusiness = !isBusiness
                      }){
                          Image(painter = painterResource(R.drawable.business), contentDescription = "business",
                               modifier = Modifier.size(30.dp), contentScale = ContentScale.FillBounds
                          )
                          Spacer(modifier = Modifier.width(8.dp))
                          Text(text = if(isBusiness) "Закрыть" else "Открыть", fontSize = 14.sp, color = colorResource(R.color.blue),
                              fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                          )
                      }
             }
             Spacer(modifier = Modifier.height(4.dp))
             AnimatedVisibility(visible = isHousing, enter = enterTransition, exit = exitTransition){
                 Row(modifier = Modifier.fillMaxWidth().height(120.dp).padding(horizontal = 8.dp)
                      .border(1.dp, color = colorResource(R.color.yellow), shape = RoundedCornerShape(8.dp))
                      .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.tryellow))),
                         RoundedCornerShape(8.dp)).padding(4.dp),
                          verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                     Column(modifier = Modifier.height(120.dp).weight(1f),
                         horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceEvenly){
                         CheckHousing("Мотоцикл", "75", isMoto,{
                             if(!isMoto){
                             if(cash > 75){
                                 capital -= 75
                                 cash -= 75
                                 isMoto = true
                                 lossinmonth += 7
                                 housing += 7
                             } else {
                                 Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                  Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                         })
                         CheckHousing("Автомобиль", "326",isAuto, {
                             if(!isAuto){
                              if(cash > 326){
                                 capital -= 326
                                 cash -= 326
                                 isAuto = true
                                 lossinmonth += 32
                                  housing += 32
                             } else {
                                 Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                 Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                         })
                         CheckHousing("Квартира", "595",isApartment, {
                             if(!isApartment){
                              if(cash > 595){
                                 capital -= 595
                                 cash -= 595
                                 isApartment = true
                                 lossinmonth += 59
                                  housing += 59
                             } else {
                                 Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                 Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                         })
                         CheckHousing("Вилла", "2047",isWilla, {
                             if(!isWilla){
                              if(cash > 2047){
                                 capital -= 2047
                                 cash -= 2047
                                 isWilla = true
                                 lossinmonth += 204
                                  housing += 204
                             } else {
                                 Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                 Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                         })
                     }
                      Column(modifier = Modifier.height(120.dp).weight(1f),
                         horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceEvenly){
                         CheckHousing("Самолет", "5145",isFly, {
                             if(!isFly){
                              if(cash > 5145){
                                 capital -= 5145
                                 cash -= 5145
                                 isFly = true
                                 lossinmonth += 514
                                  housing += 514
                             } else {
                                 Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                 Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                         })
                         CheckHousing("Дворец", "10773",isCastle, {
                             if(!isCastle){
                              if(cash > 10773){
                                 capital -= 10773
                                 cash -= 10773
                                 isCastle = true
                                 lossinmonth += 1077
                                  housing += 1077
                             } else {
                                 Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                 Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                         })
                         CheckHousing("Остров", "19562",isIsland, {
                             if(!isIsland){
                              if(cash > 19562){
                                 capital -= 19562
                                 cash -= 19562
                                 isIsland = true
                                 lossinmonth += 1956
                                  housing += 1956
                             } else {
                                 Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                 Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                         })
                     }
                 }
             }
             Spacer(modifier = Modifier.height(4.dp))
             AnimatedVisibility(visible = isBusiness, enter = enterTransition, exit = exitTransition){
                 Row(modifier = Modifier.fillMaxWidth().height(150.dp).padding(horizontal = 8.dp)
                      .border(1.dp, color = colorResource(R.color.yellow), shape = RoundedCornerShape(8.dp))
                      .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.tryellow))),
                         RoundedCornerShape(8.dp)).padding(4.dp),
                          verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                     Column(modifier = Modifier.height(150.dp).weight(1f),
                         horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceEvenly){
                         CheckHousing("Бар", "1047", isBar,{
                             if(employees >= 1){
                             if(!isBar) {
                                 if (cash > 1047) {
                                     capital -= 1047
                                     cash -= (1047 - 824)
                                     isBar = true
                                     lossinmonth += 104
                                     profitinmonth += 824
                                 } else {
                                     Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                 }
                             } else {
                                 Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                 Toast.makeText(context, "Нужен хотя бы 1 рабочий!", Toast.LENGTH_SHORT).show()
                             }
                         })
                         CheckHousing("Забегаловка", "2199", isDiner,{
                             if(employees >= 3){
                             if (!isDiner ) {
                                 if (cash > 2199) {
                                     capital -= (2199 - 1698)
                                     cash -= 2199
                                     isDiner = true
                                     lossinmonth += 219
                                     profitinmonth += 1698
                                 } else {
                                     Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                 }
                             } else {
                                 Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                  Toast.makeText(context, "Нужно хотя бы 3 рабочих!", Toast.LENGTH_SHORT).show()
                             }
                         })
                         CheckHousing("Дом", "4129", isHouse,{
                             if(employees >= 5){
                             if(!isHouse) {
                                 if (cash > 4129) {
                                     capital -= (4129 - 3119)
                                     cash -= 4129
                                     isHouse = true
                                     lossinmonth += 412
                                      profitinmonth += 3119
                                 } else {
                                     Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                 }
                             } else {
                                 Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                 Toast.makeText(context, "Нужно хотя бы 5 рабочих!", Toast.LENGTH_SHORT).show()
                             }
                         })
                         CheckHousing("Отель ***", "11804", isHotel3,{
                             if(employees >= 15){
                             if(!isHotel3) {
                                 if (cash > 11804) {
                                     capital -= (11804 - 9544)
                                     cash -= 11804
                                     isHotel3 = true
                                     lossinmonth += 1180
                                      profitinmonth += 9544
                                 } else {
                                     Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                 }
                             } else {
                                      Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                                 }
                             } else {
                                  Toast.makeText(context, "Нужно хотя бы 15 рабочих!", Toast.LENGTH_SHORT).show()
                             }
                         })
                         CheckHousing("Отель ****", "36524", isHotel4,{
                             if(employees >= 20){
                             if(!isHotel4){
                                 if (cash > 36524) {
                                     capital -= (36524 -28771)
                                     cash -= 36524
                                     isHotel4 = true
                                     lossinmonth += 3652
                                      profitinmonth += 28771
                                 } else {
                                     Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                 }
                             } else {
                                  Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                  Toast.makeText(context, "Нужно хотя бы 20 рабочих!", Toast.LENGTH_SHORT).show()
                             }
                         })
                     }
                     Column(modifier = Modifier.height(150.dp).weight(1f),
                         horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceEvenly){
                         CheckHousing("Отель *****", "55115", isHotel5,{
                             if(employees >= 25){
                             if(!isHotel5){
                                 if (cash > 55115) {
                                     capital -= (55115 - 44062)
                                     cash -= 55115
                                     isHotel5 = true
                                     lossinmonth += 5511
                                      profitinmonth += 44062
                                 } else { Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                 }
                             } else {
                                  Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                  Toast.makeText(context, "Нужно хотя бы 25 рабочих!", Toast.LENGTH_SHORT).show()
                             }
                         })
                         CheckHousing("Аэропорт", "87111", isAirport,{
                             if(employees >= 35){
                             if(!isAirport){
                                 if (cash > 87111) {
                                     capital -= (87111 - 69849)
                                     cash -= 87111
                                     isAirport = true
                                     lossinmonth += 8711
                                      profitinmonth += 69849
                                 } else {
                                     Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                 }
                             } else {
                                  Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                  Toast.makeText(context, "Нужно хотя бы 35 рабочих!", Toast.LENGTH_SHORT).show()
                             }
                         })
                         CheckHousing("Фабрика", "118167", isFactory,{
                             if(employees >= 40){
                             if(!isFactory){
                                 if (cash > 118167) {
                                     capital -= (118167 - 94304)
                                     cash -= 118167
                                     isFactory = true
                                     lossinmonth += 11816
                                      profitinmonth += 94304
                                 } else {
                                     Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                 }
                             } else {
                                  Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                  Toast.makeText(context, "Нужно хотя бы 40 рабочих!", Toast.LENGTH_SHORT).show()
                             }
                         })
                         CheckHousing("Завод", "254639", isManufacturing,{
                             if(employees >=50){
                             if(!isManufacturing){
                                 if (cash > 254639) {
                                     capital -= (254639 - 202860)
                                     cash -= 254639
                                     isManufacturing = true
                                     lossinmonth += 25463
                                      profitinmonth += 202860
                                 } else {
                                     Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                 }
                             } else {
                                  Toast.makeText(context, "Уже выбрано!", Toast.LENGTH_SHORT).show()
                             }
                             } else {
                                  Toast.makeText(context, "Нужно хотя бы 50 рабочих!", Toast.LENGTH_SHORT).show()
                             }
                         })
                     }
                 }
             }
             Spacer(modifier = Modifier.height(4.dp))
             Row(modifier = Modifier.fillMaxWidth().height(60.dp).padding(horizontal = 6.dp),
                          verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                 Box(modifier = Modifier.height(60.dp).weight(1f).padding(horizontal = 2.dp)
                     .border(2.dp, colorResource(R.color.seawave), RoundedCornerShape(8.dp))
                     .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.trseawave))),
                         RoundedCornerShape(8.dp)).clickable{

                         if (currentMonth < 12) {
                             currentMonth++
                         } else {
                             currentMonth = 1
                             age++
                             prefs.edit { putInt("age", age) }
                         }
                         prefs.edit { putInt("currentMonth", currentMonth) }
                         selectedEvents = allPossibleEvents.shuffled().take(3).map { group -> group.random() }
                         showDialogEndOfMonth.value = true

                 }, contentAlignment = Alignment.Center){
                      Text(text = "Конец\nмесяца", fontSize = 14.sp, color = colorResource(R.color.black),
                          fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                     )
                 }
                 if (showDialogEndOfMonth.value) {
                     AlertDialog(
                         onDismissRequest = { showDialogEndOfMonth.value = false },
                         containerColor = colorResource(id = R.color.trseawave),
                         text = {
                             Column(modifier = Modifier.fillMaxWidth().height(250.dp).padding(horizontal = 16.dp),
                                 horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                                 Spacer(modifier = Modifier.height(16.dp))
                                 Text(text = selectedEvents.getOrNull(0) ?: "Новость 1: Ожидание...",
                                     fontSize = 14.sp, color = colorResource(R.color.black),
                                     fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny))
                                 )
                                 Spacer(modifier = Modifier.height(8.dp))
                                 Text(text = selectedEvents.getOrNull(1) ?: "Новость 2: Ожидание...",
                                     fontSize = 14.sp, color = colorResource(R.color.black),
                                     fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny))
                                 )
                                 Spacer(modifier = Modifier.height(8.dp))
                                  Text(text = selectedEvents.getOrNull(2) ?: "Новость 3: Ожидание...",
                                     fontSize = 14.sp, color = colorResource(R.color.black),
                                     fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny))
                                 )
                                 Spacer(modifier = Modifier.height(16.dp))
                                   Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                                         verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                                       Box(modifier = Modifier.height(60.dp).weight(1f).padding(horizontal = 4.dp)
                                           .border(2.dp, colorResource(R.color.seawave), RoundedCornerShape(8.dp))
                                           .background(Brush.horizontalGradient(listOf(colorResource(R.color.white),
                                               colorResource(R.color.trseawave))), RoundedCornerShape(8.dp)).clickable {
                                               showDialogEndOfMonth.value = false
                                           }, contentAlignment = Alignment.Center) {
                                           Text(text = "Отмена", fontSize = 18.sp, color = colorResource(R.color.black),
                                               fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                                           )
                                       }
                                        Box(modifier = Modifier.height(60.dp).weight(1f).padding(horizontal = 4.dp)
                                           .border(2.dp, colorResource(R.color.seawave), RoundedCornerShape(8.dp))
                                           .background(Brush.horizontalGradient(listOf(colorResource(R.color.white),
                                               colorResource(R.color.trseawave))), RoundedCornerShape(8.dp)).clickable {

                                                if (selectedEvents.contains("- Ваш офис ограбили! Вы потеряли значительную часть наличных  -2000")) {
                                                    cash -= 2000.0
                                                    capital -= 2000.0
                                                }
                                                if (selectedEvents.contains("- Вы стали жертвой финансовой пирамиды, потеряв часть капитала  -1000")) {
                                                    cash -= 1000.0
                                                    capital -= 1000.0
                                                }
                                                if (selectedEvents.contains("- Ваша компания оказалась в центре небольшого скандала, репутация под угрозой.  -1000")) {
                                                    cash -= 1000.0
                                                    capital -= 1000.0
                                                }
                                                if (selectedEvents.contains("- Вы уволили некомпетентного сотрудника, производительность выросла, но настроение в коллективе упало.  -1000")) {
                                                    cash -= 1000.0
                                                    capital -= 1000.0
                                                }
                                                if (selectedEvents.contains("- Вы получили престижную премию 'Предприниматель года'!  +500")) {
                                                    cash += 500.0
                                                    capital += 500.0
                                                }
                                                if (selectedEvents.contains("- Вы выиграли в лотерею крупную сумму денег!  +5000")) {
                                                    cash += 5000.0
                                                    capital += 5000.0
                                                }
                                                if (selectedEvents.contains("- Дальний родственник оставил вам неожиданное наследство  +3000")) {
                                                    cash += 3000.0
                                                    capital += 3000.0
                                                }
                                                if (selectedEvents.contains("- Вас пытаются переманить конкуренты, предлагая высокую должность.  +1000")) {
                                                    cash += 1000.0
                                                    capital += 1000.0
                                                }

                                                if (!isSecurity) {
                                                    if (selectedEvents.contains("- Вас побили на улице, здоровье ухудшилось -2")) {
                                                        health -= 2
                                                    }
                                                } else {
                                                    if (selectedEvents.contains("- Вас побили на улице, здоровье ухудшилось -2")) {
                                                        Toast.makeText(context, "Была попытка нападения, но охрана Вас спасла!", Toast.LENGTH_LONG).show()
                                                    }
                                                }
                                                if (selectedEvents.contains("- Вы успешно прошли курс оздоровительных процедур, здоровье улучшилось +1")) {
                                                    health += 1
                                                }

                                                if (health < -5) {
                                                    showDialogEnd.value = true
                                                }
                                                if(selectedEvents.contains("- Ваш личный врач скончался!")){
                                                    isDoctor = false
                                                }
                                                if(selectedEvents.contains("- Ваша охрана бросила Вас, т.к. Вы не захотели платить больше!")){
                                                    isSecurity = false
                                                }


                                                val microsoft: Int
                                                val microsoftpercminus: Double
                                                val microsoftpercplus: Double
                                                if (selectedEvents.contains("- Антимонопольный иск против Microsoft, акции упали на 40%")) {
                                                    microsoft = 1
                                                    microsoftpercminus = kotlin.math.abs(capitalofMicrosoft) * 0.4
                                                    microsoftpercplus = 0.0
                                                } else if (selectedEvents.contains("- Microsoft объявляет о рекордной прибыли, акции взлетели на 30%")) {
                                                    microsoft = 2
                                                    microsoftpercplus = kotlin.math.abs(capitalofMicrosoft) * 0.3
                                                    microsoftpercminus = 0.0
                                                } else {
                                                    microsoft = (0..2).random()
                                                    microsoftpercminus = (5000..20000).random().toDouble()
                                                    microsoftpercplus = (5000..20000).random().toDouble()
                                                }
                                                when (microsoft) {
                                                    0 -> {
                                                        perMicrosoft = 0.0
                                                        firstMicrosoft = 0
                                                    }
                                                    1 -> {
                                                        val capitalBeforeChange = capitalofMicrosoft
                                                        capitalofMicrosoft -= microsoftpercminus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (microsoftpercminus / kotlin.math.abs(capitalBeforeChange)) * -100.0
                                                            perMicrosoft = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perMicrosoft = 0.0
                                                        }
                                                        isMicrosoft = false
                                                        firstMicrosoft = 1
                                                    }
                                                    2 -> {
                                                        val capitalBeforeChange = capitalofMicrosoft
                                                        capitalofMicrosoft += microsoftpercplus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (microsoftpercplus / kotlin.math.abs(capitalBeforeChange)) * 100.0
                                                            perMicrosoft = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perMicrosoft = 0.0
                                                        }
                                                        isMicrosoft = true
                                                        firstMicrosoft = 1
                                                    }
                                                    else -> {
                                                        perMicrosoft = 0.0
                                                        firstMicrosoft = 0
                                                    }
                                                }
                                                buyMicrosoft = capitalofMicrosoft / 500.0
                                                saleMicrosoft = capitalofMicrosoft / 800.0
                                                if (stockMicrosoft == 0) {
                                                    profitMicrosoft = 0.0
                                                } else {
                                                    val oldProfitMicrosoft = profitMicrosoft
                                                    profitMicrosoft = (saleMicrosoft / 10) * stockMicrosoft
                                                    val profitDeltaMicrosoft = profitMicrosoft - oldProfitMicrosoft
                                                    profitinmonth += profitDeltaMicrosoft
                                                    cash += profitMicrosoft
                                                    capital += profitMicrosoft
                                                }

                                                 val ibm: Int
                                                val ibmpercminus: Double
                                                val ibmpercplus: Double
                                                if (selectedEvents.contains("- IBM провалила квартальный отчет, акции -35%")) {
                                                    ibm = 1
                                                    ibmpercminus = kotlin.math.abs(capitalofIBM) * 0.35
                                                    ibmpercplus = 0.0
                                                } else if (selectedEvents.contains("- IBM заключила крупный госконтракт на облачные вычисления, акции +25%")) {
                                                    ibm = 2
                                                    ibmpercplus = kotlin.math.abs(capitalofIBM) * 0.25
                                                    ibmpercminus = 0.0
                                                } else {
                                                    ibm = (0..2).random()
                                                    ibmpercminus = (5000..20000).random().toDouble()
                                                    ibmpercplus = (5000..20000).random().toDouble()
                                                }
                                                when (ibm) {
                                                    0 -> {
                                                        perIBM = 0.0
                                                        firstIBM = 0
                                                    }
                                                    1 -> {
                                                        val capitalBeforeChange = capitalofIBM
                                                        capitalofIBM -= ibmpercminus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (ibmpercminus / kotlin.math.abs(capitalBeforeChange)) * -100.0
                                                            perIBM = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perIBM = 0.0
                                                        }
                                                        isIBM = false
                                                        firstIBM = 1
                                                    }
                                                    2 -> {
                                                        val capitalBeforeChange = capitalofIBM
                                                        capitalofIBM += ibmpercplus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (ibmpercplus / kotlin.math.abs(capitalBeforeChange)) * 100.0
                                                            perIBM = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perIBM = 0.0
                                                        }
                                                        isIBM = true
                                                        firstIBM = 1
                                                    }
                                                    else -> {
                                                        perIBM = 0.0
                                                        firstIBM = 0
                                                    }
                                                }
                                                buyIBM = capitalofIBM / 500.0
                                                saleIBM = capitalofIBM / 800.0
                                                if (stockIBM == 0) {
                                                    profitIBM = 0.0
                                                } else {
                                                    val oldProfitIBM = profitIBM
                                                    profitIBM = (saleIBM / 10) * stockIBM
                                                    val profitDeltaIBM = profitIBM - oldProfitIBM
                                                    profitinmonth += profitDeltaIBM
                                                    cash += profitIBM
                                                    capital += profitIBM
                                                }

                                                 val google: Int
                                                val googlepercminus: Double
                                                val googlepercplus: Double
                                                if (selectedEvents.contains("- Утечка данных в Google привела к массовым штрафам, акции -50%")) {
                                                    google = 1
                                                    googlepercminus = kotlin.math.abs(capitalofGoogle) * 0.5
                                                    googlepercplus = 0.0
                                                } else
                                                    if (selectedEvents.contains("- Новый ИИ от Google превзошел все ожидания, акции +40%")) {
                                                    google = 2
                                                    googlepercplus = kotlin.math.abs(capitalofGoogle) * 0.4
                                                    googlepercminus = 0.0
                                                } else {
                                                    google = (0..2).random()
                                                    googlepercminus = (5000..20000).random().toDouble()
                                                    googlepercplus = (5000..20000).random().toDouble()
                                                }
                                                when (google) {
                                                    0 -> {
                                                        perGoogle = 0.0
                                                        firstGoogle = 0
                                                    }
                                                    1 -> {
                                                        val capitalBeforeChange = capitalofGoogle
                                                        capitalofGoogle -= googlepercminus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (googlepercminus / kotlin.math.abs(capitalBeforeChange)) * -100.0
                                                            perGoogle = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perGoogle = 0.0
                                                        }
                                                        isGoogle = false
                                                        firstGoogle = 1
                                                    }
                                                    2 -> {
                                                        val capitalBeforeChange = capitalofGoogle
                                                        capitalofGoogle += googlepercplus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (googlepercplus / kotlin.math.abs(capitalBeforeChange)) * 100.0
                                                            perGoogle = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perGoogle = 0.0
                                                        }
                                                        isGoogle = true
                                                        firstGoogle = 1
                                                    }
                                                    else -> {
                                                        perGoogle = 0.0
                                                        firstGoogle = 0
                                                    }
                                                }
                                                buyGoogle = capitalofGoogle / 500.0
                                                saleGoogle = capitalofGoogle / 800.0
                                                if (stockGoogle == 0) {
                                                    profitGoogle = 0.0
                                                } else {
                                                    val oldProfitGoogle = profitGoogle
                                                    profitGoogle = (saleGoogle / 10) * stockGoogle
                                                    val profitDeltaGoogle = profitGoogle - oldProfitGoogle
                                                    profitinmonth += profitDeltaGoogle
                                                    cash += profitGoogle
                                                    capital += profitGoogle
                                                }

                                                 val microsystem: Int
                                                val microsystempercminus: Double
                                                val microsystempercplus: Double
                                                if (selectedEvents.contains("- Пожар на заводе Microsystem остановил производство, акции -70%")) {
                                                    microsystem = 1
                                                    microsystempercminus = kotlin.math.abs(capitalofMicrosystem) * 0.7
                                                    microsystempercplus = 0.0
                                                } else
                                                    if (selectedEvents.contains("- Microsystem разработала революционный чип, акции +60%")) {
                                                    microsystem = 2
                                                    microsystempercplus = kotlin.math.abs(capitalofMicrosystem) * 0.6
                                                    microsystempercminus = 0.0
                                                } else {
                                                    microsystem = (0..2).random()
                                                    microsystempercminus = (5000..20000).random().toDouble()
                                                    microsystempercplus = (5000..20000).random().toDouble()
                                                }
                                                when (microsystem) {
                                                    0 -> {
                                                        perMicrosystem = 0.0
                                                        firstMicrosystem = 0
                                                    }
                                                    1 -> {
                                                        val capitalBeforeChange = capitalofMicrosystem
                                                        capitalofMicrosystem -= microsystempercminus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (microsystempercminus / kotlin.math.abs(capitalBeforeChange)) * -100.0
                                                            perMicrosystem = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perMicrosystem = 0.0
                                                        }
                                                        isMicrosystem = false
                                                        firstMicrosystem = 1
                                                    }
                                                    2 -> {
                                                        val capitalBeforeChange = capitalofMicrosystem
                                                        capitalofMicrosystem += microsystempercplus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (microsystempercplus / kotlin.math.abs(capitalBeforeChange)) * 100.0
                                                            perMicrosystem = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perMicrosystem = 0.0
                                                        }
                                                        isMicrosystem = true
                                                        firstMicrosystem = 1
                                                    }
                                                    else -> {
                                                        perMicrosystem = 0.0
                                                        firstMicrosystem = 0
                                                    }
                                                }
                                                buyMicrosystem = capitalofMicrosystem / 500.0
                                                saleMicrosystem = capitalofMicrosystem / 800.0
                                                if (stockMicrosystem == 0) {
                                                    profitMicrosystem = 0.0
                                                } else {
                                                    val oldProfitMicrosystem = profitMicrosystem
                                                    profitMicrosystem = (saleMicrosystem / 10) * stockMicrosystem
                                                    val profitDeltaMicrosystem = profitMicrosystem - oldProfitMicrosystem
                                                    profitinmonth += profitDeltaMicrosystem
                                                    cash += profitMicrosystem
                                                    capital += profitMicrosystem
                                                }

                                                 val nokia: Int
                                                val nokiapercminus: Double
                                                val nokiapercplus: Double
                                                if (selectedEvents.contains("- Nokia теряет долю на рынке телеком-оборудования, акции -45%")) {
                                                    nokia = 1
                                                    nokiapercminus = kotlin.math.abs(capitalofNokia) * 0.5
                                                    nokiapercplus = 0.0
                                                } else
                                                    if (selectedEvents.contains("- Nokia выиграла патентный спор, акции +50%")) {
                                                    nokia = 2
                                                    nokiapercplus = kotlin.math.abs(capitalofNokia) * 0.5
                                                    nokiapercminus = 0.0
                                                } else {
                                                    nokia = (0..2).random()
                                                    nokiapercminus = (5000..20000).random().toDouble()
                                                    nokiapercplus = (5000..20000).random().toDouble()
                                                }
                                                when (nokia) {
                                                    0 -> {
                                                        perNokia = 0.0
                                                        firstNokia = 0
                                                    }
                                                    1 -> {
                                                        val capitalBeforeChange = capitalofNokia
                                                        capitalofNokia -= nokiapercminus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (nokiapercminus / kotlin.math.abs(capitalBeforeChange)) * -100.0
                                                            perNokia = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perNokia = 0.0
                                                        }
                                                        isNokia = false
                                                        firstNokia = 1
                                                    }
                                                    2 -> {
                                                        val capitalBeforeChange = capitalofNokia
                                                        capitalofNokia += nokiapercplus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (nokiapercplus / kotlin.math.abs(capitalBeforeChange)) * 100.0
                                                            perNokia = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perNokia = 0.0
                                                        }
                                                        isNokia = true
                                                        firstNokia = 1
                                                    }
                                                    else -> {
                                                        perNokia = 0.0
                                                        firstNokia = 0
                                                    }
                                                }
                                                buyNokia = capitalofNokia / 500.0
                                                saleNokia = capitalofNokia / 800.0
                                                if (stockNokia == 0) {
                                                    profitNokia = 0.0
                                                } else {
                                                    val oldProfitNokia = profitNokia
                                                    profitNokia = (saleNokia / 10) * stockNokia
                                                    val profitDeltaNokia = profitNokia - oldProfitNokia
                                                    profitinmonth += profitDeltaNokia
                                                    cash += profitNokia
                                                    capital += profitNokia
                                                }

                                                val chinaoil: Int
                                                val chinaoilpercminus: Double
                                                val chinaoilpercplus: Double
                                                if (selectedEvents.contains("- Разлив нефти с танкера ChinaOil грозит многомиллиардными исками, акции -90%")) {
                                                    chinaoil = 1
                                                    chinaoilpercminus = kotlin.math.abs(capitalofChinaOil) * 0.9
                                                    chinaoilpercplus = 0.0
                                                } else
                                                    if (selectedEvents.contains("- ChinaOil обнаружила новое гигантское месторождение нефти, акции +80%")) {
                                                    chinaoil = 2
                                                    chinaoilpercplus = kotlin.math.abs(capitalofChinaOil) * 0.8
                                                    chinaoilpercminus = 0.0
                                                } else {
                                                    chinaoil = (0..2).random()
                                                    chinaoilpercminus = (5000..20000).random().toDouble()
                                                    chinaoilpercplus = (5000..20000).random().toDouble()
                                                }
                                                when (chinaoil) {
                                                    0 -> {
                                                        perChinaOil = 0.0
                                                        firstChinaOil = 0
                                                    }
                                                    1 -> {
                                                        val capitalBeforeChange = capitalofChinaOil
                                                        capitalofChinaOil -= chinaoilpercminus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (chinaoilpercminus / kotlin.math.abs(capitalBeforeChange)) * -100.0
                                                            perChinaOil = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perChinaOil = 0.0
                                                        }
                                                        isChinaOil = false
                                                        firstChinaOil = 1
                                                    }
                                                    2 -> {
                                                        val capitalBeforeChange = capitalofChinaOil
                                                        capitalofChinaOil += chinaoilpercplus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (chinaoilpercplus / kotlin.math.abs(capitalBeforeChange)) * 100.0
                                                            perChinaOil = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perChinaOil = 0.0
                                                        }
                                                        isChinaOil = true
                                                        firstChinaOil = 1
                                                    }
                                                    else -> {
                                                        perChinaOil = 0.0
                                                        firstChinaOil = 0
                                                    }
                                                }
                                                buyChinaOil = capitalofChinaOil / 500.0
                                                saleChinaOil = capitalofChinaOil / 800.0
                                                if (stockChinaOil == 0) {
                                                    profitChinaOil = 0.0
                                                } else {
                                                    val oldProfitChinaOil = profitChinaOil
                                                    profitChinaOil = (saleChinaOil / 10) * stockChinaOil
                                                    val profitDeltaChinaOil = profitChinaOil - oldProfitChinaOil
                                                    profitinmonth += profitDeltaChinaOil
                                                    cash += profitChinaOil
                                                    capital += profitChinaOil
                                                }

                                                val blackrock: Int
                                                val blackrockpercminus: Double
                                                val blackrockpercplus: Double
                                                if (selectedEvents.contains("- Неудачная инвестиционная стратегия BlackRock привела к убыткам. Акции -30%")) {
                                                    blackrock = 1
                                                    blackrockpercminus = kotlin.math.abs(capitalofBlackRock) * 0.3
                                                    blackrockpercplus = 0.0
                                                } else
                                                    if (selectedEvents.contains("- BlackRock удачно инвестировал в стартап, получив сверхприбыль. Акции +35%")) {
                                                    blackrock = 2
                                                    blackrockpercplus = kotlin.math.abs(capitalofBlackRock) * 0.35
                                                    blackrockpercminus = 0.0
                                                } else {
                                                    blackrock = (0..2).random()
                                                    blackrockpercminus = (5000..20000).random().toDouble()
                                                    blackrockpercplus = (5000..20000).random().toDouble()
                                                }
                                                when (blackrock) {
                                                    0 -> {
                                                        perBlackRock = 0.0
                                                        firstBlackRock = 0
                                                    }
                                                    1 -> {
                                                        val capitalBeforeChange = capitalofBlackRock
                                                        capitalofBlackRock -= blackrockpercminus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (blackrockpercminus / kotlin.math.abs(capitalBeforeChange)) * -100.0
                                                            perBlackRock = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perBlackRock = 0.0
                                                        }
                                                        isBlackRock = false
                                                        firstBlackRock = 1
                                                    }
                                                    2 -> {
                                                        val capitalBeforeChange = capitalofBlackRock
                                                        capitalofBlackRock += blackrockpercplus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (blackrockpercplus / kotlin.math.abs(capitalBeforeChange)) * 100.0
                                                            perBlackRock = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perBlackRock = 0.0
                                                        }
                                                        isBlackRock = true
                                                        firstBlackRock = 1
                                                    }
                                                    else -> {
                                                        perBlackRock = 0.0
                                                        firstBlackRock = 0
                                                    }
                                                }
                                                buyBlackRock = capitalofBlackRock / 500.0
                                                saleBlackRock = capitalofBlackRock / 800.0
                                                if (stockBlackRock == 0) {
                                                    profitBlackRock = 0.0
                                                } else {
                                                    val oldProfitBlackRock = profitBlackRock
                                                    profitBlackRock = (saleBlackRock / 10) * stockBlackRock
                                                    val profitDeltaBlackRock = profitBlackRock - oldProfitBlackRock
                                                    profitinmonth += profitDeltaBlackRock
                                                    cash += profitBlackRock
                                                    capital += profitBlackRock
                                                }

                                                val warptech: Int
                                                val warptechpercminus: Double
                                                val warptechpercplus: Double
                                                if (selectedEvents.contains("- Клинические испытания WarpTech провалились, акции рухнули на 80%")) {
                                                    warptech = 1
                                                    warptechpercminus = kotlin.math.abs(capitalofWarpTech) * 0.8
                                                    warptechpercplus = 0.0
                                                } else
                                                    if (selectedEvents.contains("- WarpTech получила одобрение FDA на новое лекарство, акции взлетели на 150%!")) {
                                                    warptech = 2
                                                    warptechpercplus = kotlin.math.abs(capitalofWarpTech) * 1.5
                                                    warptechpercminus = 0.0
                                                } else {
                                                    warptech = (0..2).random()
                                                    warptechpercminus = (5000..20000).random().toDouble()
                                                    warptechpercplus = (5000..20000).random().toDouble()
                                                }
                                                when (warptech) {
                                                    0 -> {
                                                        perWarpTech = 0.0
                                                        firstWarpTech = 0
                                                    }
                                                    1 -> {
                                                        val capitalBeforeChange = capitalofWarpTech
                                                        capitalofWarpTech -= warptechpercminus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (warptechpercminus / kotlin.math.abs(capitalBeforeChange)) * -100.0
                                                            perWarpTech = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perWarpTech = 0.0
                                                        }
                                                        isWarpTech = false
                                                        firstWarpTech = 1
                                                    }
                                                    2 -> {
                                                        val capitalBeforeChange = capitalofWarpTech
                                                        capitalofWarpTech += warptechpercplus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (warptechpercplus / kotlin.math.abs(capitalBeforeChange)) * 100.0
                                                            perWarpTech = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perWarpTech = 0.0
                                                        }
                                                        isWarpTech = true
                                                        firstWarpTech = 1
                                                    }
                                                    else -> {
                                                        perWarpTech = 0.0
                                                        firstWarpTech = 0
                                                    }
                                                }
                                                buyWarpTech = capitalofWarpTech / 500.0
                                                saleWarpTech = capitalofWarpTech / 800.0
                                                if (stockWarpTech == 0) {
                                                    profitWarpTech = 0.0
                                                } else {
                                                    val oldProfitWarpTech = profitWarpTech
                                                    profitWarpTech = (saleWarpTech / 10) * stockWarpTech
                                                    val profitDeltaWarpTech = profitWarpTech - oldProfitWarpTech
                                                    profitinmonth += profitDeltaWarpTech
                                                    cash += profitWarpTech
                                                    capital += profitWarpTech
                                                }

                                                val mitsumi: Int
                                                val mitsumipercminus: Double
                                                val mitsumipercplus: Double
                                                if (selectedEvents.contains("- Mitsumi отзывает бракованную партию электроники, акции -55%")) {
                                                    mitsumi = 1
                                                    mitsumipercminus = kotlin.math.abs(capitalofMitsumi) * 0.55
                                                    mitsumipercplus = 0.0
                                                } else
                                                    if (selectedEvents.contains("- Игровая консоль с компонентами Mitsumi стала хитом продаж, акции +40%")) {
                                                    mitsumi = 2
                                                    mitsumipercplus = kotlin.math.abs(capitalofMitsumi) * 0.4
                                                    mitsumipercminus = 0.0
                                                } else {
                                                    mitsumi = (0..2).random()
                                                    mitsumipercminus = (5000..20000).random().toDouble()
                                                    mitsumipercplus = (5000..20000).random().toDouble()
                                                }
                                                when (mitsumi) {
                                                    0 -> {
                                                        perMitsumi = 0.0
                                                        firstMitsumi = 0
                                                    }
                                                    1 -> {
                                                        val capitalBeforeChange = capitalofMitsumi
                                                        capitalofMitsumi -= mitsumipercminus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (mitsumipercminus / kotlin.math.abs(capitalBeforeChange)) * -100.0
                                                            perMitsumi = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perMitsumi = 0.0
                                                        }
                                                        isMitsumi = false
                                                        firstMitsumi = 1
                                                    }
                                                    2 -> {
                                                        val capitalBeforeChange = capitalofMitsumi
                                                        capitalofMitsumi += mitsumipercplus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (mitsumipercplus / kotlin.math.abs(capitalBeforeChange)) * 100.0
                                                            perMitsumi = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perMitsumi = 0.0
                                                        }
                                                        isMitsumi = true
                                                        firstMitsumi = 1
                                                    }
                                                    else -> {
                                                        perMitsumi = 0.0
                                                        firstMitsumi = 0
                                                    }
                                                }
                                                buyMitsumi = capitalofMitsumi / 500.0
                                                saleMitsumi = capitalofMitsumi / 800.0
                                                if (stockMitsumi == 0) {
                                                    profitMitsumi = 0.0
                                                } else {
                                                    val oldProfitMitsumi = profitMitsumi
                                                    profitMitsumi = (saleMitsumi / 10) * stockMitsumi
                                                    val profitDeltaMitsumi = profitMitsumi - oldProfitMitsumi
                                                    profitinmonth += profitDeltaMitsumi
                                                    cash += profitMitsumi
                                                    capital += profitMitsumi
                                                }

                                                val samsung: Int
                                                val samsungpercminus: Double
                                                val samsungpercplus: Double
                                                if (selectedEvents.contains("- Проблемы с аккумуляторами в устройствах Samsung, акции -40%")) {
                                                    samsung = 1
                                                    samsungpercminus = kotlin.math.abs(capitalofSamsung) * 0.4
                                                    samsungpercplus = 0.0
                                                } else
                                                    if (selectedEvents.contains("- Новый флагманский смартфон Samsung бьет рекорды продаж, акции +30%")) {
                                                    samsung = 2
                                                    samsungpercplus = kotlin.math.abs(capitalofSamsung) * 0.3
                                                    samsungpercminus = 0.0
                                                } else {
                                                    samsung = (0..2).random()
                                                    samsungpercminus = (5000..20000).random().toDouble()
                                                    samsungpercplus = (5000..20000).random().toDouble()
                                                }
                                                when (samsung) {
                                                    0 -> {
                                                        perSamsung = 0.0
                                                        firstSamsung = 0
                                                    }
                                                    1 -> {
                                                        val capitalBeforeChange = capitalofSamsung
                                                        capitalofSamsung -= samsungpercminus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (samsungpercminus / kotlin.math.abs(capitalBeforeChange)) * -100.0
                                                            perSamsung = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perSamsung = 0.0
                                                        }
                                                        isSamsung = false
                                                        firstSamsung = 1
                                                    }
                                                    2 -> {
                                                        val capitalBeforeChange = capitalofSamsung
                                                        capitalofSamsung += samsungpercplus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (samsungpercplus / kotlin.math.abs(capitalBeforeChange)) * 100.0
                                                            perSamsung = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perSamsung = 0.0
                                                        }
                                                        isSamsung = true
                                                        firstSamsung = 1
                                                    }
                                                    else -> {
                                                        perSamsung = 0.0
                                                        firstSamsung = 0
                                                    }
                                                }
                                                buySamsung = capitalofSamsung / 500.0
                                                saleSamsung = capitalofSamsung / 800.0
                                                if (stockSamsung == 0) {
                                                    profitSamsung = 0.0
                                                } else {
                                                    val oldProfitSamsung = profitSamsung
                                                    profitSamsung = (saleSamsung / 10) * stockSamsung
                                                    val profitDeltaSamsung = profitSamsung - oldProfitSamsung
                                                    profitinmonth += profitDeltaSamsung
                                                    cash += profitSamsung
                                                    capital += profitSamsung
                                                }

                                                 val nordice: Int
                                                val nordicepercminus: Double
                                                val nordicepercplus: Double
                                                if (selectedEvents.contains("- Скандал с качеством продукции NordIce, акции -60%")) {
                                                    nordice = 1
                                                    nordicepercminus = kotlin.math.abs(capitalofNordIce) * 0.6
                                                    nordicepercplus = 0.0
                                                } else
                                                    if (selectedEvents.contains("- NordIce выходит на новый азиатский рынок, акции +25%")) {
                                                    nordice = 2
                                                    nordicepercplus = kotlin.math.abs(capitalofNordIce) * 0.25
                                                    nordicepercminus = 0.0
                                                } else {
                                                    nordice = (0..2).random()
                                                    nordicepercminus = (5000..20000).random().toDouble()
                                                    nordicepercplus = (5000..20000).random().toDouble()
                                                }
                                                when (nordice) {
                                                    0 -> {
                                                        perNordIce = 0.0
                                                        firstNordIce = 0
                                                    }
                                                    1 -> {
                                                        val capitalBeforeChange = capitalofNordIce
                                                        capitalofNordIce -= nordicepercminus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (nordicepercminus / kotlin.math.abs(capitalBeforeChange)) * -100.0
                                                            perNordIce = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perNordIce = 0.0
                                                        }
                                                        isNordIce = false
                                                        firstNordIce = 1
                                                    }
                                                    2 -> {
                                                        val capitalBeforeChange = capitalofNordIce
                                                        capitalofNordIce += nordicepercplus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (nordicepercplus / kotlin.math.abs(capitalBeforeChange)) * 100.0
                                                            perNordIce = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perNordIce = 0.0
                                                        }
                                                        isNordIce = true
                                                        firstNordIce = 1
                                                    }
                                                    else -> {
                                                        perNordIce = 0.0
                                                        firstNordIce = 0
                                                    }
                                                }
                                                buyNordIce = capitalofNordIce / 500.0
                                                saleNordIce = capitalofNordIce / 800.0
                                                if (stockNordIce == 0) {
                                                    profitNordIce = 0.0
                                                } else {
                                                    val oldProfitNordIce = profitNordIce
                                                    profitNordIce = (saleNordIce / 10) * stockNordIce
                                                    val profitDeltaNordIce = profitNordIce - oldProfitNordIce
                                                    profitinmonth += profitDeltaNordIce
                                                    cash += profitNordIce
                                                    capital += profitNordIce
                                                }

                                                 val cocacola: Int
                                                val cocacolapercminus: Double
                                                val cocacolapercplus: Double
                                                if (selectedEvents.contains("- Новый налог на сладкие напитки ударил по Coca-Cola, акции -25%")) {
                                                    cocacola = 1
                                                    cocacolapercminus = kotlin.math.abs(capitalofCocaCola) * 0.25
                                                    cocacolapercplus = 0.0
                                                } else
                                                    if (selectedEvents.contains("- Coca-Cola отчиталась о рекордных продажах в летний сезон, акции +20%")) {
                                                    cocacola = 2
                                                    cocacolapercplus = kotlin.math.abs(capitalofCocaCola) * 0.2
                                                    cocacolapercminus = 0.0
                                                } else {
                                                    cocacola = (0..2).random()
                                                    cocacolapercminus = (5000..20000).random().toDouble()
                                                    cocacolapercplus = (5000..20000).random().toDouble()
                                                }
                                                when (cocacola) {
                                                    0 -> {
                                                        perCocaCola = 0.0
                                                        firstCocaCola = 0
                                                    }
                                                    1 -> {
                                                        val capitalBeforeChange = capitalofCocaCola
                                                        capitalofCocaCola -= cocacolapercminus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (cocacolapercminus / kotlin.math.abs(capitalBeforeChange)) * -100.0
                                                            perCocaCola = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perCocaCola = 0.0
                                                        }
                                                        isCocaCola = false
                                                        firstCocaCola = 1
                                                    }
                                                    2 -> {
                                                        val capitalBeforeChange = capitalofCocaCola
                                                        capitalofCocaCola += cocacolapercplus
                                                        if (capitalBeforeChange != 0.0) {
                                                            val calculatedPercent = (cocacolapercplus / kotlin.math.abs(capitalBeforeChange)) * 100.0
                                                            perCocaCola = String.format("%.1f", calculatedPercent).replace(",", ".").toDouble()
                                                        } else {
                                                            perCocaCola = 0.0
                                                        }
                                                        isCocaCola = true
                                                        firstCocaCola = 1
                                                    }
                                                    else -> {
                                                        perCocaCola = 0.0
                                                        firstCocaCola = 0
                                                    }
                                                }
                                                buyCocaCola = capitalofCocaCola / 500.0
                                                saleCocaCola = capitalofCocaCola / 800.0
                                                if (stockCocaCola == 0) {
                                                    profitCocaCola = 0.0
                                                } else {
                                                    val oldProfitCocaCola = profitCocaCola
                                                    profitCocaCola = (saleCocaCola / 10) * stockCocaCola
                                                    val profitDeltaCocaCola = profitCocaCola - oldProfitCocaCola
                                                    profitinmonth += profitDeltaCocaCola
                                                    cash += profitCocaCola
                                                    capital += profitCocaCola
                                                }

                                                cash -= lossinmonth
                                                capital -= lossinmonth
                                                showDialogEndOfMonth.value = false
                                           }, contentAlignment = Alignment.Center) {
                                           Text(text = "Хорошо", fontSize = 18.sp, color = colorResource(R.color.black),
                                               fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                                           )
                                       }
                                   }
                                 Spacer(modifier = Modifier.height(16.dp))

                             }
                         },
                         confirmButton = {},
                         dismissButton = {})
                 }
                 if (showDialogEnd.value) {
                     AlertDialog(
                         onDismissRequest = { showDialogEnd.value = false },
                         containerColor = colorResource(id = R.color.trred),
                         text = {
                              Column(modifier = Modifier.fillMaxWidth().height(150.dp).padding(horizontal = 16.dp),
                                 horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                                 Spacer(modifier = Modifier.height(8.dp))
                                 Text(text = "Ваше здоровье Вас подвело, вы умерли!",
                                     fontSize = 18.sp, color = colorResource(R.color.black),
                                     fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                                     textAlign = TextAlign.Center
                                 )
                                 Spacer(modifier = Modifier.height(16.dp))
                                  Box(modifier = Modifier.height(60.dp).width(200.dp).padding(horizontal = 4.dp)
                                           .border(2.dp, colorResource(R.color.red), RoundedCornerShape(8.dp))
                                           .background(Brush.horizontalGradient(listOf(colorResource(R.color.white),
                                               colorResource(R.color.red))), RoundedCornerShape(8.dp)).clickable {
                                               showDialogEnd.value = false
                                                //
                                           }, contentAlignment = Alignment.Center) {
                                           Text(text = "Понятно", fontSize = 18.sp, color = colorResource(R.color.black),
                                               fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                                           )
                                       }
                                 Spacer(modifier = Modifier.height(16.dp))

                             }
                         },
                         confirmButton = {},
                         dismissButton = {})
                 }
                 Box(modifier = Modifier.height(60.dp).weight(1f).padding(horizontal = 2.dp)
                     .border(2.dp, colorResource(R.color.orange), RoundedCornerShape(8.dp))
                     .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.trorange))),
                         RoundedCornerShape(8.dp)).clickable{
                     showDialogSociety.value = true
                 }, contentAlignment = Alignment.Center){
                      Text(text = "Положение\nв обществе", fontSize = 14.sp, color = colorResource(R.color.blue),
                          fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                     )
                 }
                 if (showDialogSociety.value) {
                     AlertDialog(
                         onDismissRequest = { showDialogSociety.value = false },
                         containerColor = colorResource(id = R.color.trorange),
                         text = {
                              Column(modifier = Modifier.fillMaxWidth().height(100.dp).padding(horizontal = 16.dp),
                                 horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                                 Spacer(modifier = Modifier.height(8.dp))
                                  Text(text = when(society){
                                      0 -> "Обычный человек!"
                                      1 -> "Теперь Вы барыга!"
                                      2 -> "Теперь Вы валютчик!"
                                      3 -> "Теперь Вы перекупщик!"
                                      4 -> "Теперь Вы предприниматель!"
                                      5 -> "Теперь Вы бизнесмен!"
                                      else -> "Обычный человек!"
                                  }, fontSize = 20.sp, color = colorResource(R.color.black), fontWeight = FontWeight.Bold,
                                      fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                                 )
                                 Spacer(modifier = Modifier.height(24.dp))
                                   Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                                         verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                                       Box(modifier = Modifier.height(60.dp).weight(1f).padding(horizontal = 4.dp)
                                           .border(2.dp, colorResource(R.color.orange), RoundedCornerShape(8.dp))
                                           .background(Brush.horizontalGradient(listOf(colorResource(R.color.white),
                                               colorResource(R.color.trorange))), RoundedCornerShape(8.dp)).clickable {
                                               showDialogSociety.value = false
                                           }, contentAlignment = Alignment.Center) {
                                           Text(text = "Отмена", fontSize = 18.sp, color = colorResource(R.color.blue),
                                               fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                                           )
                                       }
                                        Box(modifier = Modifier.height(60.dp).weight(1f).padding(horizontal = 4.dp)
                                           .border(2.dp, colorResource(R.color.orange), RoundedCornerShape(8.dp))
                                           .background(Brush.horizontalGradient(listOf(colorResource(R.color.white),
                                               colorResource(R.color.trorange))), RoundedCornerShape(8.dp)).clickable {
                                               showDialogSociety.value = false
                                           }, contentAlignment = Alignment.Center) {
                                           Text(text = "Хорошо", fontSize = 18.sp, color = colorResource(R.color.blue),
                                               fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                                           )
                                       }
                                   }
                                 Spacer(modifier = Modifier.height(16.dp))

                             }
                         },
                         confirmButton = {},
                         dismissButton = {})
                 }
                 Box(modifier = Modifier.height(60.dp).weight(1f).padding(horizontal = 2.dp)
                     .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(8.dp))
                     .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.trgray))),
                         RoundedCornerShape(8.dp)).clickable{
                     showDialogCredit.value = true
                 }, contentAlignment = Alignment.Center){
                      Text(text = "Норма\nкредита", fontSize = 14.sp, color = colorResource(R.color.red),
                          fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                     )
                 }
                 if (showDialogCredit.value) {
                     AlertDialog(
                         onDismissRequest = { showDialogCredit.value = false },
                         containerColor = colorResource(id = R.color.trgray),
                         text = {
                              Column(modifier = Modifier.fillMaxWidth().height(100.dp).padding(horizontal = 16.dp),
                                 horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                                 Spacer(modifier = Modifier.height(8.dp))
                                 //
                                 Spacer(modifier = Modifier.height(24.dp))
                                   Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                                         verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                                       Box(modifier = Modifier.height(60.dp).weight(1f).padding(horizontal = 4.dp)
                                           .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(8.dp))
                                           .background(Brush.horizontalGradient(listOf(colorResource(R.color.white),
                                               colorResource(R.color.trgray))), RoundedCornerShape(8.dp)).clickable {
                                               showDialogCredit.value = false
                                           }, contentAlignment = Alignment.Center) {
                                           Text(text = "Отмена", fontSize = 18.sp, color = colorResource(R.color.red),
                                               fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                                           )
                                       }
                                        Box(modifier = Modifier.height(60.dp).weight(1f).padding(horizontal = 4.dp)
                                           .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(8.dp))
                                           .background(Brush.horizontalGradient(listOf(colorResource(R.color.white),
                                               colorResource(R.color.trgray))), RoundedCornerShape(8.dp)).clickable {
                                               showDialogCredit.value = false
                                                //
                                           }, contentAlignment = Alignment.Center) {
                                           Text(text = "Хорошо", fontSize = 18.sp, color = colorResource(R.color.red),
                                               fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                                           )
                                       }
                                   }
                                 Spacer(modifier = Modifier.height(16.dp))

                             }
                         },
                         confirmButton = {},
                         dismissButton = {})
                 }
                 Box(modifier = Modifier.height(60.dp).weight(1f).padding(horizontal = 2.dp)
                     .border(2.dp, colorResource(R.color.black), RoundedCornerShape(8.dp))
                     .background(Brush.horizontalGradient(listOf(colorResource(R.color.white), colorResource(R.color.trblack))),
                         RoundedCornerShape(8.dp)).clickable{
                     showDialogNewGame.value = true
                 }, contentAlignment = Alignment.Center){
                      Text(text = "Начать\nзаново", fontSize = 14.sp, color = colorResource(R.color.white),
                          fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                     )
                 }
                 if (showDialogNewGame.value) {
                     AlertDialog(
                         onDismissRequest = { showDialogNewGame.value = false },
                         containerColor = colorResource(id = R.color.trblack),
                         text = {
                              Column(modifier = Modifier.fillMaxWidth().height(100.dp).padding(horizontal = 16.dp),
                                 horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                                 Spacer(modifier = Modifier.height(8.dp))
                                 //
                                 Spacer(modifier = Modifier.height(24.dp))
                                   Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                                         verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                                       Box(modifier = Modifier.height(60.dp).weight(1f).padding(horizontal = 4.dp)
                                           .border(2.dp, colorResource(R.color.black), RoundedCornerShape(8.dp))
                                           .background(Brush.horizontalGradient(listOf(colorResource(R.color.white),
                                               colorResource(R.color.trblack))), RoundedCornerShape(8.dp)).clickable {
                                               showDialogNewGame.value = false
                                           }, contentAlignment = Alignment.Center) {
                                           Text(text = "Отмена", fontSize = 18.sp, color = colorResource(R.color.white),
                                               fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                                           )
                                       }
                                        Box(modifier = Modifier.height(60.dp).weight(1f).padding(horizontal = 4.dp)
                                           .border(2.dp, colorResource(R.color.black), RoundedCornerShape(8.dp))
                                           .background(Brush.horizontalGradient(listOf(colorResource(R.color.white),
                                               colorResource(R.color.trblack))), RoundedCornerShape(8.dp)).clickable {
                                               showDialogNewGame.value = false
                                                //
                                           }, contentAlignment = Alignment.Center) {
                                           Text(text = "Хорошо", fontSize = 18.sp, color = colorResource(R.color.white),
                                               fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                                           )
                                       }
                                   }
                                 Spacer(modifier = Modifier.height(16.dp))

                             }
                         },
                         confirmButton = {},
                         dismissButton = {})
                 }
             }
             Spacer(modifier = Modifier.height(8.dp))
         }
     }
}


@Composable
fun PlayerPhotoSectionResult(initialImageUri: Uri?, onImageSelected: (Uri?) -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var selectedImageUri by remember { mutableStateOf(initialImageUri) }

     LaunchedEffect(initialImageUri) {
        if (initialImageUri != null && initialImageUri.toString() == "empty") {
            selectedImageUri = null
        }
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
            val savedUri = saveImageToFile(context, it)
            selectedImageUri = savedUri
            onImageSelected(savedUri)
        } ?: run {
            selectedImageUri = null
            onImageSelected(null)
        }
        }
    )
    val legacyPhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
            val savedUri = saveImageToFile(context, it)
            selectedImageUri = savedUri
            onImageSelected(savedUri)
        } ?: run {
            selectedImageUri = null
            onImageSelected(null)
        }
        }
    )
    val launchPhotoPicker = {
        scope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                legacyPhotoPickerLauncher.launch("image/*")
            }
        }
    }
        if (selectedImageUri != null) {
            AsyncImage(model = ImageRequest.Builder(context).data(selectedImageUri).crossfade(true).build(),
                contentDescription = "Selected photo",
                modifier = Modifier.size(40.dp).clip(CircleShape).clickable { launchPhotoPicker() },
                contentScale = ContentScale.Crop
            )
        } else {
            Image(painter = painterResource(R.drawable.playericon), contentDescription = "User photo placeholder",
                modifier = Modifier.size(40.dp).clip(CircleShape).clickable { launchPhotoPicker() },
                contentScale = ContentScale.FillBounds
            )
        }
}
fun saveImageToFile(context: Context, uri: Uri): Uri {
    val inputStream = context.contentResolver.openInputStream(uri)
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val storageDir: File = File(context.getExternalFilesDir(null), "images")
    if (!storageDir.exists()) {
        storageDir.mkdirs()
    }
    val photoFile = File(storageDir, "JPEG_${timeStamp}.jpg")
    if (!photoFile.exists()) {
        val outputStream = FileOutputStream(photoFile)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
    }
    return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", photoFile)
}
@Composable
fun ItemBank(summa: String, onMinus: () -> Unit, onPlus: () -> Unit){
    val scope = rememberCoroutineScope()
    var isMinus by remember { mutableStateOf(false) }
    var isPlus by remember { mutableStateOf(false) }
    val colorminus = Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.red)))
    val colorplus = Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.green)))
    Row(){
        Card(modifier = Modifier.size(20.dp).clickable{
                scope.launch {
                    isMinus = true
                    delay(150)
                    isMinus = false
                }
            onMinus()
        }, elevation = 2.dp, border = BorderStroke(1.dp, colorResource(R.color.gray)) ){
            Box(modifier = Modifier.fillMaxSize().background(if(isMinus) colorminus else
                Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.white)))),
                contentAlignment = Alignment.Center){
                 Text(text = "-", fontSize = 16.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
            }
        }
        Spacer(modifier = Modifier.width(2.dp))
        Card(modifier = Modifier.width(120.dp).height(20.dp).clickable{
             scope.launch {
                    isPlus = true
                    delay(150)
                    isPlus = false
                }
            onPlus()
        }, elevation = 2.dp, border = BorderStroke(1.dp, colorResource(R.color.gray))){
            Box(modifier = Modifier.fillMaxSize().background(if(isPlus) colorplus else
                Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.white)))),
                contentAlignment = Alignment.Center){
                 Text(text = summa, fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
            }
        }
    }
}
@Composable
fun ItemBirga(namecompany: String, percents: Double, indicator: Boolean, first: Int){
    val colorminus = Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.white), colorResource(R.color.red)))
    val colorplus = Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.white), colorResource(R.color.green)))

    Card(modifier = Modifier.fillMaxWidth().height(24.dp), elevation = 2.dp, border = BorderStroke(1.dp, colorResource(R.color.gray))){
         Row(modifier = Modifier.fillMaxSize().background(if(first == 0){
            Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.white), colorResource(R.color.white)))
         } else {
              if(indicator) colorplus else colorminus
         }).padding(4.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = namecompany, fontSize = 14.sp, color = colorResource(R.color.green),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(4.dp))
             Row(verticalAlignment = Alignment.CenterVertically){
                 Image(painter = painterResource(if (indicator) R.drawable.arrowup else R.drawable.arrowdown),
                     contentDescription = "iconbank", modifier = Modifier.size(16.dp), contentScale = ContentScale.FillBounds
                 )
                 Spacer(modifier = Modifier.width(4.dp))
                 Text(text = "$percents%", fontSize = 12.sp, color = colorResource(R.color.green))
             }

    }
    }
}
@Composable
fun NameCompany(company: String, profit: Double, onBuy: () -> Unit, onSale: () -> Unit){
     val scope = rememberCoroutineScope()
    var isMinus by remember { mutableStateOf(false) }
    var isPlus by remember { mutableStateOf(false) }
    val colorminus = Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.red)))
    val colorplus = Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.green)))
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
         Card(modifier = Modifier.size(20.dp).clickable{
                scope.launch {
                    isMinus = true
                    delay(150)
                    isMinus = false
                }
            onSale()
        }, elevation = 2.dp, border = BorderStroke(1.dp, colorResource(R.color.gray)) ){
            Box(modifier = Modifier.fillMaxSize().background(if(isMinus) colorminus else
                Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.white)))),
                contentAlignment = Alignment.Center){
                 Text(text = "-", fontSize = 16.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
            }
        }
        Spacer(modifier = Modifier.width(2.dp))
        Card(modifier = Modifier.width(95.dp).height(20.dp).clickable{
             scope.launch {
                    isPlus = true
                    delay(150)
                    isPlus = false
                }
            onBuy()
        }, elevation = 2.dp, border = BorderStroke(1.dp, colorResource(R.color.gray))){
            Box(modifier = Modifier.fillMaxSize().background(if(isPlus) colorplus else
                Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.white)))),
                contentAlignment = Alignment.Center){
                 Text(text = company, fontSize = 14.sp, color = colorResource(if(profit < 0.0) R.color.red else R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
            }
        }
    }
}
@Composable
fun Coins(coins: Double){
    Text(text = "${coins.roundToInt()}", fontSize = 12.sp, color = colorResource(if(coins < 0) R.color.red else R.color.green))
}
@Composable
fun CoinsPercents(coinspercents : Int, coins: Double){
    Text(text = "$coinspercents", fontSize = 12.sp, color = colorResource(if(coins.roundToInt() < 0) R.color.red else R.color.green),
    fontWeight = FontWeight.Bold
    )
}
@Composable
fun CheckItem(text: String, coast: String, isOk: Boolean, onPlus: () -> Unit){
    val scope = rememberCoroutineScope()
    var isPlus by remember { mutableStateOf(false) }
    val colorplus = Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.green)))
    Row(verticalAlignment = Alignment.CenterVertically){
        Image(painter = painterResource(if(isOk) R.drawable.checkok else R.drawable.checkempty), contentDescription = "checks",
            modifier = Modifier.size(24.dp), contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(2.dp))
        Card(modifier = Modifier.width(100.dp).height(20.dp).clickable{
             scope.launch {
                    isPlus = true
                    delay(150)
                    isPlus = false
                }
            onPlus()
        }, elevation = 2.dp, border = BorderStroke(1.dp, colorResource(R.color.gray))){
            Box(modifier = Modifier.fillMaxSize().background(if(isPlus) colorplus else
                Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.white)))),
                contentAlignment = Alignment.Center){
                 Text(text = text, fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
            }
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = coast, fontSize = 12.sp, color = colorResource(R.color.green),
            fontFamily = FontFamily(Font(R.font.coiny)),
        )
    }
}
@Composable
fun ItemEmployee(summa: String, onMinus: () -> Unit, onPlus: () -> Unit){
    val scope = rememberCoroutineScope()
    var isMinus by remember { mutableStateOf(false) }
    var isPlus by remember { mutableStateOf(false) }
    val colorminus = Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.red)))
    val colorplus = Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.green)))
    Row(verticalAlignment = Alignment.CenterVertically){
        Spacer(modifier = Modifier.width(2.dp))
        Card(modifier = Modifier.size(20.dp).clickable{
                scope.launch {
                    isMinus = true
                    delay(150)
                    isMinus = false
                }
            onMinus()
        }, elevation = 2.dp, border = BorderStroke(1.dp, colorResource(R.color.gray)) ){
            Box(modifier = Modifier.fillMaxSize().background(if(isMinus) colorminus else
                Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.white)))),
                contentAlignment = Alignment.Center){
                 Text(text = "-", fontSize = 16.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
            }
        }
        Spacer(modifier = Modifier.width(4.dp))
        Card(modifier = Modifier.width(100.dp).height(20.dp).clickable{
             scope.launch {
                    isPlus = true
                    delay(150)
                    isPlus = false
                }
            onPlus()
        }, elevation = 2.dp, border = BorderStroke(1.dp, colorResource(R.color.gray))){
            Box(modifier = Modifier.fillMaxSize().background(if(isPlus) colorplus else
                Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.white)))),
                contentAlignment = Alignment.Center){
                 Text(text = summa, fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
            }
        }
        Spacer(modifier = Modifier.width(4.dp))
         Text(text = "40", fontSize = 12.sp, color = colorResource(R.color.green),
            fontFamily = FontFamily(Font(R.font.coiny)),
        )
    }
}
@Composable
fun LawerEmployeeActive(text: String, onActive: () -> Unit){
    val scope = rememberCoroutineScope()
    var isPlus by remember { mutableStateOf(false) }
    val colorplus = Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.green)))
    Row(verticalAlignment = Alignment.CenterVertically){
        Card(modifier = Modifier.fillMaxWidth().height(30.dp).padding(horizontal = 8.dp).clickable{
             scope.launch {
                    isPlus = true
                    delay(150)
                    isPlus = false
                }
            onActive()
        }, elevation = 2.dp, border = BorderStroke(1.dp, colorResource(R.color.violet))){
            Box(modifier = Modifier.fillMaxSize().background(if(isPlus) colorplus else
                Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.white)))),
                contentAlignment = Alignment.Center){
                 Text(text = text, fontSize = 16.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
            }
        }
    }
}
@Composable
fun CheckHousing(text: String, coast: String, isOk: Boolean, onPlus: () -> Unit){
    val scope = rememberCoroutineScope()
    var isPlus by remember { mutableStateOf(false) }
    val colorplus = Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.green)))
    Row(verticalAlignment = Alignment.CenterVertically){
        Image(painter = painterResource(if(isOk) R.drawable.checkok else R.drawable.checkempty), contentDescription = "checks",
            modifier = Modifier.size(24.dp), contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(2.dp))
        Card(modifier = Modifier.width(90.dp).height(20.dp).clickable{
             scope.launch {
                    isPlus = true
                    delay(150)
                    isPlus = false
                }
            onPlus()
        }, elevation = 2.dp, border = BorderStroke(1.dp, colorResource(R.color.gray))){
            Box(modifier = Modifier.fillMaxSize().background(if(isPlus) colorplus else
                Brush.verticalGradient(listOf(colorResource(R.color.white), colorResource(R.color.white)))),
                contentAlignment = Alignment.Center){
                 Text(text = text, fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
            }
        }
         Spacer(modifier = Modifier.width(4.dp))
        Text(text = coast, fontSize = 12.sp, color = colorResource(R.color.green),
            fontFamily = FontFamily(Font(R.font.coiny)),
        )
    }
}

