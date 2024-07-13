# 코틀린의 이모저모
- 타입에 대한 별칭을 줄 수 있는 typealias 라는 키워드가 존재합니다.
- Import 당시 이름을 바꿀 수 있는 as import 기능이 존재합니다.
- 변수를 한 번에 선언할 수 있는 구조분해 기능이 있으며 componentN 함수를 사용합니다.
- for문, while문과 달리 forEach에는 break와 continue를 사용할 수 없습니다.
- takeIf와 takeUnless를 활용해 코드양을 줄이고 method chaining을 활용할 수 있습니다.

# 코틀린의 scope function
- 코틀린의 scope function은 일시적인 영역을 만들어 코드를 더 간결하게 하거나, method chain에 활용됩니다.
- scope function을 사용한 코드는 사람에 따라 가독성을 다르게 느낄 수 있기 때문에 함께 프로덕트를 만들어 가는 팀끼리 convention을 잘 정해야 합니다.