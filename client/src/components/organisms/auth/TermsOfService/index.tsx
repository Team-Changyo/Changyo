import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import Button from 'components/organisms/common/Button';
import { TermsOfServiceContainer } from './style';

interface ITermsOfServiceProps {
	setStep: Dispatch<SetStateAction<number>>;
}
function TermsOfService({ setStep }: ITermsOfServiceProps) {
	const [isDone, setIsDone] = useState(false);
	const [agreeAll, setAgreeAll] = useState(false);
	const [agreeFirst, setAgreeFirst] = useState(false);
	const [agreeSecond, setAgreeSecond] = useState(false);
	const [agreeThird, setAgreethird] = useState(false);

	const handleClick = () => {
		if (isDone) {
			setStep(1);
		} else {
			// TODO : 토스트 메시지로 변경할 것.
			console.log('모두 체크해라');
		}
	};

	const handleAgreeAllClick = () => {
		if (agreeAll) {
			setAgreeAll(false);
			setAgreeFirst(false);
			setAgreeSecond(false);
			setAgreethird(false);
		} else {
			setAgreeAll(true);
			setAgreeFirst(true);
			setAgreeSecond(true);
			setAgreethird(true);
		}
	};

	useEffect(() => {
		if (!agreeFirst || !agreeSecond || !agreeThird) {
			setAgreeAll(false);
			setIsDone(false);
		} else {
			setIsDone(true);
			setAgreeAll(true);
		}
	}, [agreeFirst, agreeSecond, agreeThird]);

	return (
		<TermsOfServiceContainer>
			{/* 전체 동의 */}
			<div className="agree-all" onClick={handleAgreeAllClick} role="presentation">
				<span>
					<Check fill={agreeAll ? 'var(--main-color)' : 'var(--gray-500)'} />
					전체동의 (선택 포함)
				</span>
			</div>
			{/* 약관 목록 */}
			<div className="agree-list">
				{/* 첫 번째 약관 */}
				<div onClick={() => setAgreeFirst(!agreeFirst)} role="presentation">
					<div>
						<Check fill={agreeFirst ? 'var(--main-color)' : 'var(--gray-500)'} />
					</div>
					<span>만 14세 이상 이용, 서비스 이용약관, 개인정보 수집 및 이용 동의 (필수)</span>
				</div>
				{/* 두 번째 약관 */}
				<div onClick={() => setAgreeSecond(!agreeSecond)} role="presentation">
					<div>
						<Check fill={agreeSecond ? 'var(--main-color)' : 'var(--gray-500)'} />
					</div>
					<span>개인정보 수집 및 이용 동의 (필수)</span>
				</div>
				{/* 세 번째 약관 */}
				<div onClick={() => setAgreethird(!agreeThird)} role="presentation">
					<div>
						<Check fill={agreeThird ? 'var(--main-color)' : 'var(--gray-500)'} />
					</div>
					<span>‘챙겨요’ 마케팅 알림 수신 동의 (선택)</span>
				</div>
			</div>
			{/* 동의하고 계속하기 */}
			<div className="next-btn">
				<Button handleClick={handleClick} type={isDone ? 'Primary' : 'Normal'} text="동의하고 계속하기" />
			</div>
		</TermsOfServiceContainer>
	);
}

export default TermsOfService;
