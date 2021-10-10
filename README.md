commonMain defines:

```
open class BaseClass(internal val internalVal: String)
class FooBar(val exposedVar: Int) : BaseClass("internalValue") {
    fun compute() = 1 + 1
}
```

When exported for iOS, the headers in XCFramework contains:

```

__attribute__((swift_name("BaseClass")))
@interface KmpTestBaseClass : KmpTestBase
- (instancetype)initWithInternalVal:(NSString *)internalVal __attribute__((swift_name("init(internalVal:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FooBar")))
@interface KmpTestFooBar : KmpTestBaseClass
- (instancetype)initWithExposedVar:(int32_t)exposedVar __attribute__((swift_name("init(exposedVar:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithInternalVal:(NSString *)internalVal __attribute__((swift_name("init(internalVal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (int32_t)compute __attribute__((swift_name("compute()")));
@property (readonly) int32_t exposedVar __attribute__((swift_name("exposedVar")));
@end;
```

Problem:
When in Kotlin, I've only access to the constructor with `exposedVar` but in iOS it looks like I can use a second one:

```
- (instancetype)initWithInternalVal:(NSString *)internalVal __attribute__((swift_name("init(internalVal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
```

The attribute is 'unavailable' and the constructor should not be here from what I understand.
