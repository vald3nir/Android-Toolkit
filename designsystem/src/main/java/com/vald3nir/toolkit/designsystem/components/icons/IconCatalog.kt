package com.vald3nir.toolkit.designsystem.components.icons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.ShortText
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Addchart
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SolarPower
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.CloudSync
import androidx.compose.material.icons.outlined.Downloading
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.RemoveShoppingCart
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material.icons.outlined.Wifi
import androidx.compose.material.icons.outlined.WifiOff
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Grid3x3
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material.icons.rounded.ViewDay
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXl
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

object ToolkitIconCatalog {

    val AccountCircle = Icons.Default.AccountCircle
    val Add = Icons.Default.Add
    val AddChart = Icons.Filled.Addchart
    val AddShoppingCart = Icons.Outlined.AddShoppingCart
    val ArrowBack = Icons.AutoMirrored.Rounded.ArrowBack
    val ArrowForward = Icons.AutoMirrored.Rounded.ArrowForward
    val ArrowIndicatorRight = Icons.AutoMirrored.Filled.ArrowForwardIos
    val AttachMoney = Icons.Outlined.AttachMoney
    val Backpack = Icons.Default.Backpack
    val BarChart = Icons.Default.BarChart
    val Bookmark = Icons.Rounded.Bookmark
    val BookmarkBorder = Icons.Rounded.BookmarkBorder
    val Bookmarks = Icons.Rounded.Bookmarks
    val BookmarksBorder = Icons.Outlined.Bookmarks
    val Check = Icons.Default.Check
    val ChevronDown = Icons.Default.KeyboardArrowDown
    val ChevronLeft = Icons.Default.ChevronLeft
    val ChevronRight = Icons.Default.ChevronRight
    val ChevronUp = Icons.Default.KeyboardArrowUp
    val Close = Icons.Default.Close
    val CloudSync = Icons.Outlined.CloudSync
    val ContentCopy = Icons.Default.ContentCopy
    val DarkMode = Icons.Default.DarkMode
    val DateRange = Icons.Default.DateRange
    val Delete = Icons.Default.Delete
    val DirectionsCar = Icons.Default.DirectionsCar
    val Downloading = Icons.Outlined.Downloading
    val Edit = Icons.Default.Edit
    val Email = Icons.Default.Email
    val ExitToApp = Icons.AutoMirrored.Filled.ExitToApp
    val Favorite = Icons.Default.Favorite
    val FavoriteBorder = Icons.Default.FavoriteBorder
    val Grid3x3 = Icons.Rounded.Grid3x3
    val Home = Icons.Default.Home
    val Inbox = Icons.Default.Inbox
    val Info = Icons.Default.Info
    val LightMode = Icons.Default.LightMode
    val Logout = Icons.AutoMirrored.Filled.Logout
    val Mall = Icons.Default.LocalMall
    val Menu = Icons.Default.Menu
    val MoreVert = Icons.Default.MoreVert
    val Notifications = Icons.Default.Notifications
    val Paid = Icons.Default.Paid
    val Palette = Icons.Outlined.Palette
    val Password = Icons.Default.Password
    val PedalBike = Icons.Default.PedalBike
    val Person = Icons.Default.Person
    val Pin = Icons.Default.Pin
    val PhoneAndroid = Icons.Default.PhoneAndroid
    val QrCodeScanner = Icons.Default.QrCodeScanner
    val Remove = Icons.Default.Remove
    val RemoveCircleOutline = Icons.Default.RemoveCircleOutline
    val RemoveShoppingCart = Icons.Outlined.RemoveShoppingCart
    val Save = Icons.Default.Save
    val Science = Icons.Default.Science
    val Search = Icons.Default.Search
    val Settings = Icons.Default.Settings
    val Share = Icons.Default.Share
    val ShoppingBasket = Icons.Default.ShoppingBasket
    val ShoppingCart = Icons.Default.ShoppingCart
    val ShortText = Icons.AutoMirrored.Rounded.ShortText
    val SolarPower = Icons.Default.SolarPower
    val Star = Icons.Default.Star
    val Tag = Icons.Outlined.Tag
    val Upcoming = Icons.Rounded.Upcoming
    val UpcomingBorder = Icons.Outlined.Upcoming
    val ViewDay = Icons.Rounded.ViewDay
    val Visibility = Icons.Default.Visibility
    val VisibilityOff = Icons.Default.VisibilityOff
    val Warning = Icons.Outlined.Warning
    val Wifi = Icons.Outlined.Wifi
    val WifiOff = Icons.Outlined.WifiOff

    val allIcons = listOf(
        "AccountCircle" to AccountCircle,
        "Add" to Add,
        "AddChart" to AddChart,
        "AddShoppingCart" to AddShoppingCart,
        "ArrowBack" to ArrowBack,
        "ArrowForward" to ArrowForward,
        "ArrowIndicatorRight" to ArrowIndicatorRight,
        "AttachMoney" to AttachMoney,
        "Backpack" to Backpack,
        "BarChart" to BarChart,
        "Bookmark" to Bookmark,
        "BookmarkBorder" to BookmarkBorder,
        "Bookmarks" to Bookmarks,
        "BookmarksBorder" to BookmarksBorder,
        "Check" to Check,
        "ChevronDown" to ChevronDown,
        "ChevronLeft" to ChevronLeft,
        "ChevronRight" to ChevronRight,
        "ChevronUp" to ChevronUp,
        "Close" to Close,
        "CloudSync" to CloudSync,
        "ContentCopy" to ContentCopy,
        "DarkMode" to DarkMode,
        "DateRange" to DateRange,
        "Delete" to Delete,
        "DirectionsCar" to DirectionsCar,
        "Downloading" to Downloading,
        "Edit" to Edit,
        "Email" to Email,
        "ExitToApp" to ExitToApp,
        "Favorite" to Favorite,
        "FavoriteBorder" to FavoriteBorder,
        "Grid3x3" to Grid3x3,
        "Home" to Home,
        "Inbox" to Inbox,
        "Info" to Info,
        "LightMode" to LightMode,
        "Logout" to Logout,
        "Mall" to Mall,
        "Menu" to Menu,
        "MoreVert" to MoreVert,
        "Notifications" to Notifications,
        "Paid" to Paid,
        "Palette" to Palette,
        "Password" to Password,
        "PedalBike" to PedalBike,
        "Person" to Person,
        "Pin" to Pin,
        "PhoneAndroid" to PhoneAndroid,
        "Remove" to Remove,
        "RemoveCircleOutline" to RemoveCircleOutline,
        "RemoveShoppingCart" to RemoveShoppingCart,
        "Save" to Save,
        "Science" to Science,
        "Search" to Search,
        "Settings" to Settings,
        "Share" to Share,
        "ShoppingBasket" to ShoppingBasket,
        "ShoppingCart" to ShoppingCart,
        "ShortText" to ShortText,
        "SolarPower" to SolarPower,
        "Star" to Star,
        "Tag" to Tag,
        "Upcoming" to Upcoming,
        "UpcomingBorder" to UpcomingBorder,
        "ViewDay" to ViewDay,
        "Visibility" to Visibility,
        "VisibilityOff" to VisibilityOff,
        "Warning" to Warning,
        "Wifi" to Wifi,
        "WifiOff" to WifiOff
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            modifier = Modifier.padding(ToolkitSpacingMd),
            horizontalArrangement = Arrangement.spacedBy(ToolkitSpacingMd),
            verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd)
        ) {
            items(ToolkitIconCatalog.allIcons) { (name, icon) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ToolkitIcon(
                        imageVector = icon,
                        contentDescription = name,
                        modifier = Modifier.size(ToolkitSpacingXl),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    ToolkitText(text = name, style = ToolkitTextStyle.LabelSmall)
                }
            }
        }
    }
}