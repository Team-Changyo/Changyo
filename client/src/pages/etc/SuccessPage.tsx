import React from 'react';
import Button from 'components/organisms/common/Button';
import { useLocation, useNavigate } from 'react-router-dom';
import CompleteLottie from 'components/atoms/lottie/CompleteLottie';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import { styled } from 'styled-components';
import PageLayout from 'layouts/common/PageLayout';

const SuccessPageLayout = styled.div`
	position: relative;
	margin-top: 3rem;
	height: calc(100vh - 4rem);

	display: flex;
	flex-direction: column;

	.message {
		margin-top: 7rem;
		display: flex;
		flex-direction: column;
		align-items: center;
		gap: 10px;

		.color {
			color: var(--main-color);
		}
		h2 {
			font-size: 1.3rem;
		}
	}
	.lottie {
		margin-top: 3rem;
	}
	.next-btn {
		position: absolute;
		width: 100%;
		bottom: 2rem;
	}
`;

function SuccessPage() {
	const navigate = useNavigate();
	const { navTitle, mainMessage, message, buttonText } = useLocation().state;

	return (
		<PageLayout>
			<SuccessPageLayout>
				<SubTabNavbar text={navTitle} closePath="/" type="close" />
				<div className="message">
					<h2 className="color">{mainMessage}</h2>
					<h2>{message}</h2>
				</div>
				<div className="lottie">
					<CompleteLottie />
				</div>
				<div className="next-btn">
					<Button handleClick={() => navigate('/')} type="Primary" text={buttonText} />
				</div>
			</SuccessPageLayout>
		</PageLayout>
	);
}

export default SuccessPage;
