/**
 *
 */
function setActiveElement(curSort, curPage){
    var activeSort = document.getElementById("sort" +curSort);
    activeSort.classList.add("active");

    var activePage = document.getElementById("page" +curPage);
    activePage.classList.add("active");
}
