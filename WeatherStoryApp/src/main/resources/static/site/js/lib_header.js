
function getToken() {
	return localStorage.getItem("token");
}

const StartView = {
template: `
		<span v-if="member">
			<span v-if="memberDetail">
				<a href="javascript:getStart()" class="btn btn-primary py-3 px-5 me-3 animated fadeIn">우리 동네에 어떤 일이?</a>
			</span>
			<span v-else>
				<a href="/weatherapp/member/update" class="btn btn-primary py-3 px-5 me-3 animated fadeIn">회원상세정보 필요</a>
			</span>
		</span>
		<span v-else>
			<a href="/weatherapp/member/loginform" class="btn btn-primary py-3 px-5 me-3 animated fadeIn">시작하기</a>
		</span>
	`,
	data(){
		return {
			member : null,
			memberDetail : null
			
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
					this.memberDetail=result.memberDetail
					console.log(this.member);
					console.log(this.memberDetail);
				}
			).fail(
				e=>{
					console.log(e);
				}
			)
		}
	}
}
const startApp = Vue.createApp({
	components:{
		StartView
	}
});
startApp.mount("#startApp");

function getMemberInfo(){
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