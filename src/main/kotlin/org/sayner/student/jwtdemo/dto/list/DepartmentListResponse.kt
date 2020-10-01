package org.sayner.student.jwtdemo.dto.list

import org.sayner.student.jwtdemo.model.Department

data class DepartmentListResponse(
        val departments:List<Department>
)
