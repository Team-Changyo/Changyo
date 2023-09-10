import LoadingLottie from 'components/atoms/lottie/LoadingLottie';
import React, { useEffect, useState } from 'react';
import { styled } from 'styled-components';

const RemittingPageContainer = styled.div`
	position: relative;
	height: 100vh;
	display: flex;
	flex-direction: column;
	justify-content: center;

	.message {
		position: absolute;
		left: 50%;
		transform: translateX(-50%);
		margin-top: 10rem;
	}
`;

function RemittingPage() {
	const [message, setMessage] = useState('소중한 금액을 전달하고 있어요');

	useEffect(() => {
		setTimeout(() => {
			setMessage('잠시만 기다려주세요');
		}, 3000);
	}, []);

	return (
		<RemittingPageContainer>
			<LoadingLottie />
			<div className="message">{message}</div>
		</RemittingPageContainer>
	);
}

export default RemittingPage;
