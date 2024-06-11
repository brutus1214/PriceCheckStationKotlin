package com.example.pricecheckstationkotlin.view.ui

import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.registerReceiver
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pricecheckstationkotlin.model.data.ConnectionDB
import com.example.pricecheckstationkotlin.model.data.Department
import com.example.pricecheckstationkotlin.model.data.DepartmentList
import com.example.pricecheckstationkotlin.model.data.Item
import com.example.pricecheckstationkotlin.model.data.LoginData
import com.example.pricecheckstationkotlin.model.data.VendorList
import com.example.pricecheckstationkotlin.model.data.itemClear
import com.example.pricecheckstationkotlin.ui.theme.Purple40
import com.example.pricecheckstationkotlin.ui.theme.Purple80
import com.example.pricecheckstationkotlin.util.GlobalVariable.context
import com.example.pricecheckstationkotlin.viewmodel.PriceCheckViewModel
import kotlinx.coroutines.launch
import java.sql.ResultSet
import java.sql.Statement


@Composable
fun PriceCheckScreen(decodedData: String?)
{
    val viewModel: PriceCheckViewModel = hiltViewModel()


    val textFieldHeight: Int = 65

    var item: Item by remember {
        mutableStateOf(Item())
    }


    var text by remember {
        mutableStateOf("")
    }


    var scrollState = rememberScrollState()

    val scope = rememberCoroutineScope()

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val focusRequester = remember {
        FocusRequester()
    }

    var upc by remember() {
        mutableStateOf(decodedData.toString())
    }
    var desc by remember {
        mutableStateOf("")
    }
    var desc1 by remember {
        mutableStateOf("")
    }
    var desc2 by remember {
        mutableStateOf("")
    }
    var desc3 by remember {
        mutableStateOf("")
    }
    var retailPrice by remember {
        mutableStateOf("")
    }
    var salePrice by remember {
        mutableStateOf("")
    }
    var dept by remember {
        mutableStateOf("")
    }
    var saleStart by remember {
        mutableStateOf("")
    }
    var saleEnd by remember {
        mutableStateOf("")
    }

    var str by remember {
        mutableStateOf("")
    }

    var server by remember {
        mutableStateOf("192.168.1.200")
    }
    var userName by remember {
        mutableStateOf("sa")
    }
    var password by remember {
        mutableStateOf("L4Martss")
    }
    var dbName by remember {
        mutableStateOf("lamss")
    }

    var loginData: LoginData = LoginData()
    if ( loginData.validateUserName(userName) &&
        loginData.validateServer(server) &&
        loginData.validateDbName(dbName) &&
        loginData.validatePassword(password))
    {
        // Instantiate DB connection object
        // Connection first
        val c: ConnectionDB = ConnectionDB()
        ConnectionDB.conn = c.connDB(server, "1433", dbName, userName, password)
        val conn = ConnectionDB.conn

        // Before navigate to ItemScreen
        // If conn successful, create list of department and supplier
        // Then navigate to ItemScreen
        if (conn != null) {
            try {
                val sqlStatement: String = "SELECT ID, Name FROM Department"
                val smt: Statement = conn!!.createStatement()
                val set: ResultSet = smt.executeQuery(sqlStatement)
                var deptList = DepartmentList()

                while (set.next()) {
                    var dept: Department = Department()
                    dept.id = set.getInt("ID")
                    dept.name = set.getString("Name")
                    deptList.addDepartment(dept)
                    /*
                    Log.i(
                        "Department ---> ",
                        dept.name.toString()
                    )
                    */
                }
                Log.i("PCSK: Department index Size---> ", deptList.getIndexSize().toString())
            } catch (e: Exception) {
                Log.e(
                    "PCSK: Error ---> Department List Problem ---> ",
                    e.message.toString()
                )
            }

            // If data is add by scanner then
            if (decodedData != null) {
                itemClear(item)


                val deptList = DepartmentList()
                //val supplyList = VendorList()

                // Get Zebra scanner input and populate
                item.upc = decodedData
                Log.i("PCSK: UPC ---> ", item.upc)
                Log.i("PCSK: UPC decodedData---> ", decodedData)
                upc = decodedData.toString()
                viewModel.onEvent(ItemEvent.GetItemById(item))
                desc = item.description
                desc1 = item.desc1
                desc2 = item.desc2
                desc3 = item.desc3
                retailPrice = item.retailPrice
                salePrice = item.salePrice
                dept = deptList.getDepartmentString(item.dept.id)
                saleStart = item.saleStart
                saleEnd = item.saleEnd

            }

        }

    }
    else {
        // TODO: Error Dialog box then
    }
    //
    Scaffold(

    ) { innerPadding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Showing LA Mart
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 8.dp)
                    .height(textFieldHeight.dp)
                    .focusProperties { canFocus = false },
                value = "",
                onValueChange = {
                    desc = item.description
                },
                label = {
                    Text("LA MART ")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "LA MART"
                    )
                },
                prefix = {
                    Text("")
                },
                suffix = {
                    Text("")
                },
                supportingText = {
                    Text("")
                },
                isError = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        // TODO:
                    }
                ),
                readOnly = true
            )

                    OutlinedTextField(
                        modifier = Modifier
                            .height(textFieldHeight.dp)
                            .focusRequester(focusRequester)
                            .onFocusChanged { hasFocus ->
                                //if (hasFocus.isFocused) {
                                scope.launch {
                                    val upcText = upc
                                    //upc = upc.copy(
                                    //    selection = TextRange(0, upcText.length)
                                    //    //selection = TextRange(upcText.length)
                                    //)
                                }
                                //}
                            }
                            .focusable(),
                        textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                        value = upc,
                        onValueChange = {
                            upc = it
                        },
                        singleLine = true,
                        label = {
                            Text("UPC:")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "UPC:"
                            )
                        },
                        prefix = {
                            Text("")
                        },
                        suffix = {
                            Text("")
                        },

                        isError = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // TODO:
                            }
                        ),
                        readOnly = false
                    )


                    //
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(horizontal = 8.dp)
                            .height(textFieldHeight.dp)
                            .focusProperties { canFocus = false },
                        value = desc,
                        onValueChange = {
                            desc = item.description
                        },
                        label = {
                            Text("Desc.:")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "Desc.:"
                            )
                        },
                        prefix = {
                            Text("")
                        },
                        suffix = {
                            Text("")
                        },
                        supportingText = {
                            Text("")
                        },
                        isError = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // TODO:
                            }
                        ),
                        readOnly = true
                    )


                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(horizontal = 8.dp)
                            .weight(5f)
                            .height(textFieldHeight.dp)
                            .focusProperties { canFocus = false },
                        value = desc1,
                        onValueChange = {
                            desc1 = item.desc1
                        },
                        label = {
                            Text("Desc. 1:")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "Desc. 1:"
                            )
                        },
                        prefix = {
                            Text("")
                        },
                        suffix = {
                            Text("")
                        },
                        isError = true,
                        //visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // TODO:
                            }
                        ),
                        readOnly = true
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(horizontal = 8.dp)
                            .weight(5f)
                            .height(textFieldHeight.dp)
                            .focusProperties { canFocus = false },
                        value = desc2,
                        onValueChange = {
                            desc2 = item.desc2
                        },
                        label = {
                            Text("Desc. 2:")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "Desc. 2:"
                            )
                        },
                        prefix = {
                            Text("")
                        },
                        suffix = {
                            Text("")
                        },
                        isError = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // TODO:
                            }
                        ),
                        readOnly = true
                    )



                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(horizontal = 8.dp)
                            .weight(5f)
                            .height(textFieldHeight.dp)
                            .focusProperties { canFocus = false },
                        value = desc3,
                        onValueChange = {
                            desc3 = item.desc3
                        },
                        label = {
                            Text("Desc. 3:")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "Desc. 3:"
                            )
                        },
                        prefix = {
                            Text("")
                        },
                        suffix = {
                            Text("")
                        },
                        isError = true,
                        //visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // TODO:
                            }
                        ),
                        readOnly = true
                    )

                    // Department--
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(horizontal = 8.dp)
                            .weight(5f)
                            .height(textFieldHeight.dp)
                            .focusProperties { canFocus = false },
                        value = dept,
                        onValueChange = {
                            dept = item.dept.toString()
                        },
                        singleLine = true,
                        label = {
                            Text("Department:")
                        },
                        prefix = {
                            Text("")
                        },
                        suffix = {
                            Text("")
                        },
                        isError = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // TODO:
                            }
                        ),
                        readOnly = false
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(horizontal = 8.dp)
                            .weight(5f)
                            .height(textFieldHeight.dp)
                            .focusProperties { canFocus = false },
                        value = saleStart,
                        onValueChange = {
                            saleStart = item.saleStart.toString()
                        },
                        singleLine = true,
                        label = {
                            Text("Sale Start:")
                        },
                        prefix = {
                            Text("")
                        },
                        suffix = {
                            Text("")
                        },
                        isError = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // TODO:
                            }
                        ),
                        readOnly = false
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(horizontal = 8.dp)
                            .weight(5f)
                            .height(textFieldHeight.dp)
                            .focusProperties { canFocus = false },
                        value = saleEnd,
                        onValueChange = {
                            saleEnd = item.saleEnd.toString()
                        },
                        label = {
                            Text("Sale End:")
                        },
                        prefix = {
                            Text("$")
                        },
                        suffix = {
                            Text("")
                        },
                        isError = true,
                        //visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // TODO:
                            }
                        ),
                        readOnly = true
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(horizontal = 8.dp)
                            .weight(3.3f)
                            .height(textFieldHeight.dp)
                            .focusProperties { canFocus = false },
                        value = retailPrice,
                        onValueChange = {
                            retailPrice = item.retailPrice
                        },
                        singleLine = true,
                        label = {
                            Text("RP:")
                        },
                        prefix = {
                            Text("$")
                        },
                        suffix = {
                            Text("")
                        },
                        isError = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // TODO:
                            }
                        ),
                        readOnly = false
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(horizontal = 8.dp)
                            .weight(3.3f)
                            .height(textFieldHeight.dp)
                            .focusProperties { canFocus = false },
                        value = salePrice,
                        onValueChange = {
                            salePrice = item.salePrice
                        },
                        label = {
                            Text("SP:")
                        },
                        prefix = {
                            Text("$")
                        },
                        suffix = {
                            Text("")
                        },
                        isError = true,
                        //visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // TODO:
                            }
                        ),
                        readOnly = true
                    )

        }
    }
}

