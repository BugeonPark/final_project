
function getToken() {
	return localStorage.getItem("token");
}

const LoginView = {
template: `
	<span v-if="member">
		<div class="nav-item dropdown">
			<a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
		    	{{member.nickname}}님
		    </a>
		    <div class="dropdown-menu rounded-0 m-0">
		        <a href="/weatherapp/member/update" class="dropdown-item">회원정보수정</a>
		        <a href="javascript:logout()" class="dropdown-item">logout</a>
		    </div>
		</div>
		
	</span>
	<span v-else>
		<div class="navbar-nav ms-3">
		<a href="/weatherapp/member/loginform" class="btn btn-primary px-3 d-none d-lg-flex">로그인</a>
		<a href="/weatherapp/member/registform" class="btn btn-primary px-3 d-none d-lg-flex ml-3 mr-3 ms-4">회원가입</a>
		</div>
		
	</span>
	`,
	data(){
		return {
			member : null
		}
	},
	mounted(){
		this.fetchMember();
	},
	methods:{
		fetchMember(){
			getMemberInfo()
			.then(
				result =>{
					this.member=result;
				}
			).fail(
				e=>{
					console.log("스토리앱 에러 : " + e);
				}
			)
		}
	}
}
const loginApp = Vue.createApp({
	components:{
		LoginView
	}
});
loginApp.mount("#loginApp");

function getMemberInfo(){
	console.log("로그인 확인시 보내는  토큰 : " + getToken());
	return $.ajax({
		url:"/weatherapp/rest/member/logincheck",
		type: "GET",
		dataType:"json",
		headers:{
			"Authorization":"Bearer " + getToken()
		},
	})
	.then(
		function(result, status, xhr){
			console.log(result);
			return result;
		},
		function(xhr, status, err){
			throw new Error(err);
		}
	);
}

function logout(){
	localStorage.clear();
	location.href="/weatherapp";
}