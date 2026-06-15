package com.service.book.book;

import org.springframework.data.jpa.domain.Specification;


// يتم استخدام واجهة <Specification<T
// لإنشاء استعلامات ديناميكية تعتمد على شروط معينة. تُعتبر هذه الواجهة جزءًا من مكتبة Spring Data JPA
// وهي مبنية على مفهوم "معايير الاستعلام" (Criteria API) الخاصة بـ JPA.
public class BookSpecification {

    // يمكن أن نستخدم الـ Specification لكتابة شروط استعلام معقدة بدون الحاجة إلى كتابة SQL صريح.
    // تقوم هذه الدالة بإنشاء استعلام ديناميكي للتحقق من أن كتابًا معينًا ينتمي إلى مالك (Owner) معين بناءً على الـ ownerId.
    public static Specification<Book> withOwnerId(Integer ownerId) {
        // Predicate toPredicate(Root<T> root, @Nullable CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner").get("id"), ownerId);
    }
}

//1. الجذر (Root):
//        root يمثل الكيان الأساسي (Book) الذي يتم تطبيق الاستعلام عليه. يمكن استخدام root للوصول إلى خصائص الكيان.
//        في هذا المثال، نحن نقوم بالوصول إلى الخاصية owner في الكيان Book ثم نصل إلى الـ id الخاص بالمالك من خلال root.get("owner").get("id").

//2. الاستعلام (Query):
//        query يمثل الاستعلام نفسه، ولكننا لا نستخدمه مباشرة في هذا الكود. يمكن استخدامه في حالات متقدمة لتخصيص الاستعلام (مثل ترتيب النتائج، أو استخدام الـ joins).

//3. الـ criteriaBuilder:
//        criteriaBuilder يستخدم لبناء الشروط المنطقية في الاستعلام. يوفر العديد من الدوال مثل equal, like, greaterThan, وغيرها.
//        هنا، نحن نستخدم criteriaBuilder.equal لإنشاء شرط يفحص ما إذا كانت قيمة الـ id الخاص بالمالك تساوي ownerId