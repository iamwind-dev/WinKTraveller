package vku.duongdlt.winktraveller.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vku.duongdlt.winktraveller.R
import vku.duongdlt.winktraveller.model.Booking
import vku.duongdlt.winktraveller.model.Tour
import vku.duongdlt.winktraveller.model.User
import vku.duongdlt.winktraveller.navigation.Route
import vku.duongdlt.winktraveller.navigation.Screen
import vku.duongdlt.winktraveller.util.formatCurrency

@Composable
fun ConfirmationScreen(routeState: MutableState<Route>, booking: Booking, tour: Tour){
    Surface(modifier = Modifier.fillMaxSize()) {
        InvoiceBillPage(routeState,booking, tour)
    }
}


@Composable
fun InvoiceBillPage(routeState: MutableState<Route>,
    booking: Booking,
    tour: Tour
) {
    var customerName by remember { mutableStateOf("") }
    var invoiceNumber by remember { mutableStateOf("1") }
    var itemDescription by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var totalAmount by remember { mutableStateOf("") }
    Surface(modifier = Modifier.fillMaxSize()) {

    Row {
        Column(modifier = Modifier.padding(top = 50.dp)) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Column {


                    Image(
                        painter = painterResource(id = R.drawable.icone_de_coche_bleue),
                        contentDescription = "",
                        modifier = Modifier
                            .size(90.dp)

                    )
                    Text(
                        text = "Compelete",
                        style = TextStyle(fontSize = 24.sp, color = Color.Black)
                    )
                }
            }
            DashedBorderBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Invoice Bill",
                        style = TextStyle(fontSize = 24.sp, color = Color.Black)
                    )


                    BasicTextField(
                        value = booking.booking_custumer_name,
                        onValueChange = { customerName = it },
                        textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column {
                                    Text("Customer Name", style = TextStyle(color = Color.Gray))

                                    innerTextField()
                                }

                            }
                        }
                    )



                    BasicTextField(
                        value = invoiceNumber,
                        onValueChange = { invoiceNumber = it },
                        textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column {
                                    Text("Invoice Number", style = TextStyle(color = Color.Gray))

                                    innerTextField()
                                }
                            }
                        }
                    )



                    BasicTextField(
                        value = tour.tour_name,
                        onValueChange = { itemDescription = tour.tour_name },
                        textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column {
                                    Text("Tour", style = TextStyle(color = Color.Gray))

                                    innerTextField()
                                }
                            }
                        }
                    )



                    BasicTextField(
                        value = booking.booking_custumer_email,
                        onValueChange = { quantity = booking.booking_custumer_email },
                        textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column {
                                    Text("Email", style = TextStyle(color = Color.Gray))
                                    innerTextField()
                                }
                            }
                        }
                    )



                    BasicTextField(
                        value = tour.tour_create_date,
                        onValueChange = { tour.tour_create_date },
                        textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column {
                                    Text("Book date", style = TextStyle(color = Color.Gray))

                                    innerTextField()
                                }
                            }
                        }
                    )



                    BasicTextField(
                        value = tour.tour_start_date,
                        onValueChange = { tour.tour_start_date },
                        textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column {
                                    Text("start date", style = TextStyle(color = Color.Gray))

                                    innerTextField()
                                }
                            }
                        }
                    )



                    BasicTextField(
                        value = tour.tour_end_date,
                        onValueChange = { price = tour.tour_end_date },
                        textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column {
                                    Text("start date", style = TextStyle(color = Color.Gray))

                                    innerTextField()
                                }
                            }
                        }
                    )

                    BasicTextField(
                        value = booking.booking_capacity.toString(),
                        onValueChange = { price = it },
                        textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column {
                                    Text("capacity", style = TextStyle(color = Color.Gray))
                                    innerTextField()
                                }
                            }
                        }
                    )

                    BasicTextField(
                        value = formatCurrency(tour.tour_price),
                        onValueChange = { price = formatCurrency(tour.tour_price) },
                        textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column {
                                    Text("Price", style = TextStyle(color = Color.Gray))
                                    innerTextField()
                                }
                            }
                        }
                    )


                    BasicTextField(
                        value = formatCurrency(booking.booking_total),
                        onValueChange = { totalAmount = formatCurrency(booking.booking_total) },
                        textStyle = TextStyle(fontSize = 18.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.Gray.copy(alpha = 0.1f))
                            ) {
                                Column {
                                    Text("Total", style = TextStyle(color = Color.Gray))
                                    innerTextField()
                                }
                            }
                        }
                    )



                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(3.dp),
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.TextColor)),
                            onClick = {
                                routeState.value = Route(screen = Screen.HomeScreen)
                            }
                        ) {
                            Text(text = "Back Home")
                        }


                }


            }
        }

    }
        }
}
@Composable
fun DashedBorderBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val strokeWidth = 1.dp.toPx()
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

            translate(left = strokeWidth / 2, top = strokeWidth / 2) {
                drawRect(
                    color = Color.Blue,
                    size = size.copy(width = size.width - strokeWidth, height = size.height - strokeWidth),
                    style = Stroke(width = strokeWidth, pathEffect = pathEffect)
                )
            }
        }
        content()
    }
}