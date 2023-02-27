package com.example.learnkotlin.kotlin_in_action.chapter7

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// 리스너가 필요한 클래스는 PropertyChangeAware(helper class)를 상속 받아서 changeSupport에 접근할 수 있다.
class PersonDPEX1(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int = age
        set(newValue) { // newValue 필드에 저장할 때 로직 작성
            val oldValue = field // backing field에 접근할 때 "field" 식별자 사용
            field = newValue
            changeSupport.firePropertyChange( // 프로퍼티 변경을 리스너에게 통지
                "age", oldValue, newValue
            )
        }

    var salary: Int = salary
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("salary", oldValue, newValue)
        }
}

// helper class for using PropertyChangeSupport
open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)
    // PropertyChangeSupport 인스턴스 changeSupport 필드에 저장

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }


}

// 수정 전
/*class ObservableProperty(
        val propName: String, var propValue: Int,
        val changeSupport: PropertyChangeSupport
    ) {

        fun getValue(): Int = propValue
        fun setValue(newValue: Int) {
            val oldValue = propValue
            propValue = newValue
            changeSupport.firePropertyChange(propName, oldValue, newValue)
        }
    }*/


/*class PersonDPEX2(val name: String, age: Int, salary: Int) : PropertyChangeAware() {

    // ObservableProrperty를 만들고 게터와 세터에서
    // ObservableProrperty에게 작업을 위임하는 준비코드가 많이 필요함.
    val _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) {
            _age.setValue(value)
        }

    val _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) {
            _salary.setValue(value)
        }
}*/

// 위임 프로퍼티 사용
class ObservableProperty(
    var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {
    // 프로퍼티 값을 저장하고 그 값이 밖쒸면 자동으로 변경 통지를 전달해주는 클래스
    operator fun getValue(p: PersonDPEX3, prop: KProperty<*>): Int = propValue
    operator fun setValue(p: PersonDPEX3, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

class PersonDPEX3(
    val name: String, age: Int, salary: Int
) : PropertyChangeAware() {
    // by
    var age: Int by ObservableProperty(age, changeSupport)
    var salary: Int by ObservableProperty(salary, changeSupport)



    // 코틀린이 위임 객체를 감춰진 프로퍼티(delegate)에 저장하고,
    /*@NotNull
    private final ObservableProperty age$delegate;
    @NotNull
    private final ObservableProperty salary$delegate;*/

    // by 키워드가 자동으로 준비 코드를 생성해줌
    // 주 객체의 프로퍼티를 읽거나 쓸 때마다 위임 객체의 getValue와 setValue를 호출해준다.
    /*public final int getAge() {
        return this.age$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public final void setAge(int var1) {
        this.age$delegate.setValue(this, $$delegatedProperties[0], var1);
    }

    public final int getSalary() {
        return this.salary$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public final void setSalary(int var1) {
        this.salary$delegate.setValue(this, $$delegatedProperties[1], var1);
    }*/
}


// ObservableProperty와 비슷한 역할을 하는 표준 라이브러리
// PropertyChangeSupport 클래스와 연결되어있지 않으므로 사용방법을 알려주는 람다를
// 표준 라이브러리 클래스에 넘겨야한다.
class PersonDPEX4(
    val name: String, age: Int, salary: Int
) : PropertyChangeAware() {
    private val observer = { // 사용방법을 알려주는 람다
        prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}



fun main() {
    val p1 = PersonDPEX1("Jina", 24, 3000)

    p1.addPropertyChangeListener(PropertyChangeListener { event ->
        println(
            "Property ${event.propertyName} changed" +
                    "from ${event.oldValue} to ${event.newValue}"
        )
    })

    p1.age = 22
    p1.salary = 1000

    /*val p2 = PersonDPEX2("ponyo", 5, 10000)
    p2.addPropertyChangeListener(PropertyChangeListener { event ->
        println(
            "Property ${event.propertyName} changed" +
                    "from ${event.oldValue} to ${event.newValue}"
        )
    })
    p2.age = 10
    p2.salary = 20000*/
}