<div class="sidebar-menu toggle-others">
    <div class="sidebar-menu-inner">
        <ul id="main-menu" class="main-menu">
            <!-- add class "multiple-expanded" to allow multiple submenus to open -->
            <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
            <li><a href="#"> <i class="fa-newspaper-o"></i> 
                    <span class="title">Avvisi e News</span>
                </a>
                <ul>
                    <li><a href="#"> <span class="title">Inserisci News</span>
                        </a></li>
                    <li><a href="#"> <span class="title">Modifica News</span>
                        </a></li>
                </ul></li>
            <li><a href="#"> <i class="linecons-tag"></i> 
                    <span class="title">Dipartimenti</span>
                </a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/ShowInsertDepartmentServlet"> <span class="title">Inserisci Dipartimento</span>
                        </a></li>
                    <li><a href="${pageContext.request.contextPath}/ShowDepartmentServlet"> <span class="title">Modifica Dipartimento</span>
                        </a></li>
                </ul></li>
            <li><a href="#"> <i class="linecons-tag"></i> 
                    <span class="title">Corsi Di Laurea</span>
                </a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/ShowInsertDegreeServlet"> <span class="title">Inserisci Corso di Laurea</span>
                        </a></li>
                    <li><a href="${pageContext.request.contextPath}/ShowDegreeServlet"> <span class="title">Modifica Corso di Laurea</span>
                        </a></li>
                </ul></li>
                <li><a href="#"> <i class="linecons-tag"></i> 
                    <span class="title">Curriculum</span>
                </a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/ShowInsertCurriculumServlet"> <span class="title">Inserisci Curriculum</span>
                        </a></li>
                    <li><a href="${pageContext.request.contextPath}/ShowCurriculumServlet"> <span class="title">Modifica Curriculum</span>
                        </a></li>
                </ul></li>
                <li><a href="#"> <i class="linecons-tag"></i> 
                    <span class="title">Insegnamenti</span>
                </a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/ShowInsertTeachingServlet"> <span class="title">Inserisci Insegnamento</span>
                        </a></li>
                    <li><a href="${pageContext.request.contextPath}/ShowListTeachingServlet"> <span class="title">Visualizza Insegnamenti</span>
                        </a></li>
                    <li><a href="${pageContext.request.contextPath}/ShowInsertClassServlet"> <span class="title">Inserisci Classe</span>
                        </a></li>
                    <li><a href="${pageContext.request.contextPath}/ShowInsertModuleServlet"> <span class="title">Inserisci Modulo</span>
                        </a></li>
                </ul></li>
                
        </ul>
    </div>
</div>