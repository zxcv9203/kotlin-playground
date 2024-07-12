# 코틀린에서 object 키워드를 다루는 방법
- Java의 static 변수와 함수를 만드려면 Kotlin에서는 companion object를 사용해야 합니다.
- companion object도 하나의 객체로 간주되기 때문에 이름을 붙일 수 있고, 다른 타입을 상속받을 수도 있습니다.
- Kotlin에서 싱글톤 클래스를 만들 때 object 키워드를 사용합니다.
- Kotlin에서 익명 클래스를 만들 때 object : 타입을 사용합니다.

# 코틀린에서 다양한 클래스를 다루는 방법
- Kotlin의 Data class를 사용하면 equals, hashCode, toString을 자동으로 만들어 줍니다.
- Kotlin의 Enum Class는 Java의 Enum Class와 비슷하지만, when과 함께 사용함으로써 큰 장점을 가질 수 있습니다.
- Enum Class보다 유연하지만 하위 클래스를 제한하는 Sealed Class를 사용할 수도 있습니다.