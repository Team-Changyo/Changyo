import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import TextInput from 'components/atoms/auth/TextInput';
import Button from 'components/organisms/common/Button';
import { RegisterFormContainer } from './style';

interface IRegisterFormProps {
	setIsDone: Dispatch<SetStateAction<boolean>>;
}
function RegisterForm({ setIsDone }: IRegisterFormProps) {
	const [userId, setUserId] = useState('');
	const [userPw, setUserPw] = useState('');
	const [userPwConfirm, setuserPwConfirm] = useState('');
	const [userName, setUserName] = useState('');
	const [phoneNumber, setPhoneNumber] = useState('');
	const [certNumber, setCertNumber] = useState('');

	// 휴대폰 인증 관련
	const [isCertified, setIsCertified] = useState(false);
	const [activeCert, setActiveCert] = useState(false);

	useEffect(() => {
		if (userPw && userPwConfirm && userName && phoneNumber && isCertified) {
			setIsDone(true);
		} else {
			setIsDone(false);
		}
	}, [userId, userPw, userPwConfirm, userName, phoneNumber, isCertified]);

	return (
		<RegisterFormContainer>
			{/* 로그인 정보(아이디, 비밀번호) */}
			<div className="login-info">
				<TextInput placeholder="아이디" value={userId} setValue={setUserId} />
				<TextInput placeholder="비밀번호" value={userPw} setValue={setUserPw} />
				<TextInput placeholder="비밀번호 확인" value={userPwConfirm} setValue={setuserPwConfirm} />
			</div>

			{/* 사용자 정보(이름, 전화번호) */}
			<div className="user-info">
				<TextInput placeholder="이름" value={userName} setValue={setUserName} />
				<TextInput placeholder="전화번호" value={phoneNumber} setValue={setPhoneNumber} />

				{isCertified ? (
					// 휴대폰 인증 완료
					<Button handleClick={() => {}} text="휴대폰 인증 완료" type="Normal" />
				) : (
					// 휴대폰 인증 전
					<>
						{activeCert ? (
							// 휴대폰 인증번호 전송 완료
							<>
								<TextInput placeholder="인증번호" value={certNumber} setValue={setCertNumber} />
								<Button
									handleClick={
										certNumber.length >= 6
											? () => {
													setActiveCert(false);
													setIsCertified(true);
											  }
											: () => {}
									}
									text="인증번호 확인"
									type={certNumber.length >= 6 ? 'Primary' : 'Normal'}
								/>
							</>
						) : (
							// 휴대폰 인증번호 전송 전
							<Button
								handleClick={phoneNumber.length >= 11 ? () => setActiveCert(true) : () => {}}
								text="휴대폰 인증번호 전송"
								type={phoneNumber.length >= 11 ? 'Primary' : 'Normal'}
							/>
						)}
					</>
				)}
			</div>
		</RegisterFormContainer>
	);
}

export default RegisterForm;
