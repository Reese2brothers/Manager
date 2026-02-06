package com.manahu.manager.screens

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manahu.manager.R
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.core.content.edit
import androidx.core.net.toUri
import kotlinx.coroutines.delay

@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current as Activity
    val prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    var isBank by remember { mutableStateOf(false) }
    var isBirga by remember { mutableStateOf(false) }

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

    var profitMicrosoft by remember { mutableStateOf(0) }
    var profitIBM by remember { mutableStateOf(0) }
    var profitGoogle by remember { mutableStateOf(0) }
    var profitMicrosystem by remember { mutableStateOf(0) }
    var profitNokia by remember { mutableStateOf(0) }
    var profitChinaOil by remember { mutableStateOf(0) }
    var profitBlackRock by remember { mutableStateOf(0) }
    var profitWarpTech by remember { mutableStateOf(0) }
    var profitMitsumi by remember { mutableStateOf(0) }
    var profitSamsung by remember { mutableStateOf(0) }
    var profitNordIce by remember { mutableStateOf(0) }
    var profitCocaCola by remember { mutableStateOf(0) }

    var buyMicrosoft by remember { mutableStateOf(348) }
    var buyIBM by remember { mutableStateOf(224) }
    var buyGoogle by remember { mutableStateOf(405) }
    var buyMicrosystem by remember { mutableStateOf(187) }
    var buyNokia by remember { mutableStateOf(251) }
    var buyChinaOil by remember { mutableStateOf(303) }
    var buyBlackRock by remember { mutableStateOf(296) }
    var buyWarpTech by remember { mutableStateOf(164) }
    var buyMitsumi by remember { mutableStateOf(172) }
    var buySamsung by remember { mutableStateOf(340) }
    var buyNordIce by remember { mutableStateOf(158) }
    var buyCocaCola by remember { mutableStateOf(99) }

    var saleMicrosoft by remember { mutableStateOf(118) }
    var saleIBM by remember { mutableStateOf(105) }
    var saleGoogle by remember { mutableStateOf(214) }
    var saleMicrosystem by remember { mutableStateOf(76) }
    var saleNokia by remember { mutableStateOf(113) }
    var saleChinaOil by remember { mutableStateOf(96) }
    var saleBlackRock by remember { mutableStateOf(108) }
    var saleWarpTech by remember { mutableStateOf(72) }
    var saleMitsumi by remember { mutableStateOf(75) }
    var saleSamsung by remember { mutableStateOf(137) }
    var saleNordIce by remember { mutableStateOf(66) }
    var saleCocaCola by remember { mutableStateOf(39) }

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

    var capitalofMicrosoft by remember { mutableStateOf(643298671) }
    var capitalofIBM by remember { mutableStateOf(497864379) }
    var capitalofGoogle by remember { mutableStateOf(965398122) }
    var capitalofMicrosystem by remember { mutableStateOf(265098582) }
    var capitalofNokia by remember { mutableStateOf(348673981) }
    var capitalofChinaOil by remember { mutableStateOf(547298761) }
    var capitalofBlackRock by remember { mutableStateOf(512976490) }
    var capitalofWarpTech by remember { mutableStateOf(286504338) }
    var capitalofMitsumi by remember { mutableStateOf(265981218) }
    var capitalofSamsung by remember { mutableStateOf(628465104) }
    var capitalofNordIce by remember { mutableStateOf(244977541) }
    var capitalofCocaCola by remember { mutableStateOf(176409872) }

    var capital by remember { mutableStateOf(1000) }
    var cash by remember { mutableStateOf(1000) }
    var housing by remember { mutableStateOf(0) }
    var profitinmonth by remember { mutableStateOf(0) }
    var lossinmonth by remember { mutableStateOf(0) }
    var employees by remember { mutableStateOf(0) }



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
    var isAgeSet by remember { mutableStateOf(prefs.getBoolean("isAgeSet", false)) }
    var age by remember { mutableStateOf(prefs.getString("age", "18") ?: "18") }
    var name by remember { mutableStateOf(prefs.getString("name", "Игрок") ?: "Игрок") }
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
                     Text(text = "$capital", fontSize = 20.sp, color = colorResource(R.color.green),
                         fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny))
                     )
                 }
                 }
                 Row(verticalAlignment = Alignment.CenterVertically){
                     val ageInt = age.toIntOrNull() ?: 18
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
                                     OutlinedTextField(value = age, onValueChange = { newValue -> age = newValue },
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
                                                      putString("age", age)
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
             Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                 .border(1.dp, color = colorResource(R.color.green), shape = RoundedCornerShape(8.dp))
                 .padding(8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
                 Column(modifier = Modifier.height(90.dp), horizontalAlignment = Alignment.Start,
                     verticalArrangement = Arrangement.SpaceEvenly){
                     Text(text = "Наличные $cash", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                     Text(text = "Недвижимость $housing", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                      Text(text = "Доход в месяц $profitinmonth", fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                     Text(text = "Расход в месяц $lossinmonth", fontSize = 14.sp, color = colorResource(R.color.green),
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
                      .clickable{ isBirga = !isBirga }, contentAlignment = Alignment.Center){
                       Text(text = if(isBirga) "З\nа\nк\nр\nы\nт\nь" else "П\nо\nд\nр\nо\nб\nн\nе\nе", fontSize = 14.sp, color = colorResource(R.color.blue),
                          fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                     )
                  }
              }
             Spacer(modifier = Modifier.height(4.dp))
              AnimatedVisibility(visible = isBirga,  enter = enterTransition, exit = exitTransition ) {
                      Row(modifier = Modifier.fillMaxWidth().height(334.dp).padding(horizontal = 8.dp)
                      .border(1.dp, color = colorResource(R.color.blue), shape = RoundedCornerShape(8.dp)).padding(4.dp),
                          verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                          Column(modifier = Modifier.fillMaxHeight().width(120.dp),
                              horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                              Spacer(modifier = Modifier.height(4.dp))
                              Text(text = "Компания", fontSize = 14.sp, color = colorResource(R.color.orange),
                                  fontFamily = FontFamily(Font(R.font.coiny)),
                              )
                              Spacer(modifier = Modifier.height(20.dp))
                              NameCompany("Microsoft", {
                                  if (stockMicrosoft < 100) {
                                      stockMicrosoft++
                                       if (cash > 348) {
                                          capital -= 348
                                          cash -= 348
                                         val perc10 = (saleMicrosoft * 10) / 100
                                          profitMicrosoft += perc10
                                          capital += perc10
                                          cash += perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockMicrosoft > 0) {
                                      stockMicrosoft--
                                      capital += 118
                                      cash += 118
                                      val perc10 = (saleMicrosoft * 10) / 100
                                      profitMicrosoft -= perc10
                                      profitinmonth -= perc10
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("IBM", {
                                  if (stockIBM < 100) {
                                      stockIBM++
                                       if (cash > 224) {
                                          capital -= 224
                                          cash -= 224
                                         val perc10 = (saleIBM * 10) / 100
                                          profitIBM += perc10
                                          capital += perc10
                                          cash += perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockIBM > 0) {
                                      stockIBM--
                                       capital += 105
                                      cash += 105
                                      val perc10 = (saleIBM * 10) / 100
                                      profitIBM -= perc10
                                      profitinmonth -= perc10
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Google", {
                                  if (stockGoogle < 100) {
                                      stockGoogle++
                                       if (cash > 405) {
                                          capital -= 405
                                          cash -= 405
                                         val perc10 = (saleGoogle * 10) / 100
                                          profitGoogle += perc10
                                          capital += perc10
                                          cash += perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockGoogle > 0) {
                                      stockGoogle--
                                       capital += 214
                                      cash += 214
                                      val perc10 = (saleGoogle * 10) / 100
                                      profitGoogle -= perc10
                                      profitinmonth -= perc10
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Microsystem", {
                                  if (stockMicrosystem < 100) {
                                      stockMicrosystem++
                                      if (cash > 187) {
                                          capital -= 187
                                          cash -= 187
                                         val perc10 = (saleMicrosystem * 10) / 100
                                          profitMicrosystem += perc10
                                          capital += perc10
                                          cash += perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockMicrosystem > 0) {
                                      stockMicrosystem--
                                       capital += 76
                                      cash += 76
                                      val perc10 = (saleMicrosystem * 10) / 100
                                      profitMicrosystem -= perc10
                                      profitinmonth -= perc10
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Nokia", {
                                  if (stockNokia < 100) {
                                      stockNokia++
                                      if (cash > 251) {
                                          capital -= 251
                                          cash -= 251
                                         val perc10 = (saleNokia * 10) / 100
                                          profitNokia += perc10
                                          capital += perc10
                                          cash += perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockNokia > 0) {
                                      stockNokia--
                                       capital += 113
                                      cash += 113
                                      val perc10 = (saleNokia * 10) / 100
                                      profitNokia -= perc10
                                      profitinmonth -= perc10
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("China Oil", {
                                  if (stockChinaOil < 100) {
                                      stockChinaOil++
                                       if (cash > 303) {
                                          capital -= 303
                                          cash -= 303
                                         val perc10 = (saleChinaOil * 10) / 100
                                          profitChinaOil += perc10
                                          capital += perc10
                                          cash += perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  } }, {
                                  if (stockChinaOil > 0) {
                                      stockChinaOil--
                                       capital += 96
                                      cash += 96
                                      val perc10 = (saleChinaOil * 10) / 100
                                      profitChinaOil -= perc10
                                      profitinmonth -= perc10
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Black Rock", {
                                  if(stockBlackRock < 100) {
                                  stockBlackRock++
                                       if (cash > 296) {
                                          capital -= 296
                                          cash -= 296
                                         val perc10 = (saleBlackRock * 10) / 100
                                          profitBlackRock += perc10
                                          capital += perc10
                                          cash += perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                              }}, { if(stockBlackRock > 0) {
                                      stockBlackRock--
                                       capital += 108
                                      cash += 108
                                      val perc10 = (saleBlackRock * 10) / 100
                                      profitBlackRock -= perc10
                                      profitinmonth -= perc10
                                  }})
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Warp Tech", {
                                  if (stockWarpTech < 100) {
                                      stockWarpTech++
                                      if (cash > 164) {
                                          capital -= 164
                                          cash -= 164
                                         val perc10 = (saleWarpTech * 10) / 100
                                          profitWarpTech += perc10
                                          capital += perc10
                                          cash += perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockWarpTech > 0) {
                                      stockWarpTech--
                                       capital += 72
                                      cash += 72
                                      val perc10 = (saleWarpTech * 10) / 100
                                      profitWarpTech -= perc10
                                      profitinmonth -= perc10
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Mitsumi", {
                                  if (stockMitsumi < 100) {
                                      stockMitsumi++
                                       if (cash > 172) {
                                          capital -= 172
                                          cash -= 172
                                         val perc10 = (saleMitsumi * 10) / 100
                                          profitMitsumi += perc10
                                          capital += perc10
                                          cash += perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockMitsumi > 0) {
                                      stockMitsumi--
                                      capital += 75
                                      cash += 75
                                      val perc10 = (saleMitsumi * 10) / 100
                                      profitMitsumi -= perc10
                                      profitinmonth -= perc10
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Samsung", {
                                  if (stockSamsung < 100) {
                                      stockSamsung++
                                      if (cash > 340) {
                                          capital -= 340
                                          cash -= 340
                                         val perc10 = (saleSamsung * 10) / 100
                                          profitSamsung += perc10
                                          capital += perc10
                                          cash += perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockSamsung > 0) {
                                      stockSamsung--
                                       capital += 137
                                      cash += 137
                                      val perc10 = (saleSamsung * 10) / 100
                                      profitSamsung -= perc10
                                      profitinmonth -= perc10
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Nord Ice", {
                                  if (stockNordIce < 100) {
                                      stockNordIce++
                                       if (cash > 158) {
                                          capital -= 158
                                          cash -= 158
                                         val perc10 = (saleNordIce * 10) / 100
                                          profitNordIce += perc10
                                          capital += perc10
                                          cash += perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockNordIce > 0) {
                                      stockNordIce--
                                       capital += 66
                                      cash += 66
                                      val perc10 = (saleNordIce * 10) / 100
                                      profitNordIce -= perc10
                                      profitinmonth -= perc10
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                              NameCompany("Coca Cola", {
                                  if (stockCocaCola < 100) {
                                      stockCocaCola++
                                      if (cash > 99) {
                                          capital -= 99
                                          cash -= 99
                                         val perc10 = (saleCocaCola * 10) / 100
                                          profitCocaCola += perc10
                                          capital += perc10
                                          cash += perc10
                                          profitinmonth += perc10
                                      } else {
                                          Toast.makeText(context, "У вас не хватает наличных!", Toast.LENGTH_SHORT).show()
                                      }
                                  }
                              }, {
                                  if (stockCocaCola > 0) {
                                      stockCocaCola--
                                      capital += 39
                                      cash += 39
                                      val perc10 = (saleCocaCola * 10) / 100
                                      profitCocaCola -= perc10
                                      profitinmonth -= perc10
                                  }
                              })
                              Spacer(modifier = Modifier.height(4.dp))
                          }
                          Column(modifier = Modifier.fillMaxHeight().weight(1f),
                              horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
                              Text(text = "доход\n в месяц", fontSize = 11.sp, color = colorResource(R.color.orange),
                                  fontFamily = FontFamily(Font(R.font.coiny)), textAlign = TextAlign.Center
                              )
                              Spacer(modifier = Modifier.height(14.dp))
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
                               Text(text = "купля", fontSize = 11.sp, color = colorResource(R.color.orange),
                                   fontFamily = FontFamily(Font(R.font.coiny)),
                               )
                              Spacer(modifier = Modifier.height(24.dp))
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
                               Text(text = "продажа", fontSize = 11.sp, color = colorResource(R.color.orange),
                                   fontFamily = FontFamily(Font(R.font.coiny)),
                               )
                              Spacer(modifier = Modifier.height(24.dp))
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
                               Text(text = "%\nакций", fontSize = 12.sp, color = colorResource(R.color.orange),
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
fun NameCompany(company: String, onBuy: () -> Unit, onSale: () -> Unit){
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
                 Text(text = company, fontSize = 14.sp, color = colorResource(R.color.green),
                          fontFamily = FontFamily(Font(R.font.coiny)),
                     )
            }
        }
    }
}
@Composable
fun Coins(coins: Int){
    Text(text = "$coins", fontSize = 12.sp, color = colorResource(if(coins < 0) R.color.red else R.color.green))
}
@Composable
fun CoinsPercents(coinspercents : Int, coins: Int){
    Text(text = "$coinspercents", fontSize = 12.sp, color = colorResource(if(coins < 0) R.color.red else R.color.green),
    fontWeight = FontWeight.Bold
    )
}
