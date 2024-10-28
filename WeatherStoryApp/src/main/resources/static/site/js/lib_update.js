
const UpdateView = {
template: `
	<div class="card-body">
    	<input type="hidden" name="member_idx" v-model="member_idx">
    	
    	<span v-if="sns_name=='homepage'">
	        <div class="form-group row">
	            <label for="inputName" class="col-sm-2 col-form-label">ID</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" id="sns" v-model="member.uid" readonly>
	            </div>
	        </div>
    	</span>
    	<span v-if="sns_name=='homepage'">
	        <div class="form-group row">
	            <label for="inputName2" class="col-sm-2 col-form-label">이름</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" id="inputName2" name="nickname"
	                    v-model="member.nickname">
	            </div>
	        </div>
    	</span>
    	<span v-else>
    		<div class="form-group row">
	            <label for="inputName2" class="col-sm-2 col-form-label">이름</label>
	            <div class="col-sm-10">
	                <input type="text" class="form-control" id="inputName2" name="nickname"
	                    value="{{member.nickname}}" readonly>
	            </div>
        	</div>
    	</span>
    	
    	<div class="form-group row">
	        <label for="inputEmail3" class="col-sm-2 col-form-label">주소</label>
	        <div class="col-sm-10">
	            <input name="zonecode" type="hidden" id="sample4_postcode" placeholder="우편번호">
	            <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
	            <span v-if="memberDetail">
	                <input name="roadAddress" type="text" id="sample4_roadAddress" placeholder="도로명주소"
	                    v-model="roadAddress" readonly>
	            </span>
	            <span v-else>
	                <input name="roadAddress" type="text" id="sample4_roadAddress" placeholder="도로명주소" readonly>
	            </span>
	            <input type="hidden" name="jibunAddress" id="sample4_jibunAddress" placeholder="지번주소">
	            <span id="guide" style="color: #999; display: none"></span>
	
	
	            <input type="hidden" name="address" id="address">
	            <input type="hidden" name="bname" id="bname">
	            <input type="hidden" name="bnameEnglish" id="bnameEnglish">
	            <input type="hidden" name="sido" id="sido">
	            <input type="hidden" name="sidoEnglish" id="sidoEnglish">
	            <input type="hidden" name="sigungu" id="sigungu">
	            <input type="hidden" name="sigunguEnglish" id="sigunguEnglish">
	            <input type="hidden" name="roadAddressEnglish" id="roadAddressEnglish">
	            <input type="hidden" name="dong" id="dong">
	        </div>
    	</div>
    
    </div>
	`,
	data(){
		return {
			member : null,
			member_idx : null,
			memberDetail : null,
			sns : null,
			sns_name : null,
			roadAddress : null
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
					this.member_idx=result.member_idx;
					this.memberDetail=result.memberDetail;
					this.sns=result.sns;
					this.sns_name=result.sns.sns_name;
					this.roadAddress=result.memberDetail.roadAddress;
				}
			).fail(
				e=>{
					location.href="/weatherstory";
				}
			)
		}
	}
}
const updateApp = Vue.createApp({
	components:{
		UpdateView
	}
});
updateApp.mount("#updateApp");

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