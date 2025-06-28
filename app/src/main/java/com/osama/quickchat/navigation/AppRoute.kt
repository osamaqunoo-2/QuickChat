sealed class AppRoute(val route: String) {
    object Home : AppRoute("home")           // شاشة الرئيسية (المنتجات)
    object Messages : AppRoute("messages")   // شاشة المحادثات
    object Profile : AppRoute("profile")     // شاشة البروفايل (لاحقًا)
    object Chat : AppRoute("chat")           // شاشة محادثة داخلية (تفاصيل محادثة)
}
