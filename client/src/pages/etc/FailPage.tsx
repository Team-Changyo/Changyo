import React from 'react';
import Button from 'components/organisms/common/Button';
import { useLocation, useNavigate } from 'react-router-dom';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import { styled } from 'styled-components';
import PageLayout from 'layouts/common/PageLayout';
import FailLottie from 'components/atoms/lottie/FailLottie';

const FailPageLayout = styled.div`
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

function FailPage() {
	const navigate = useNavigate();
	const location = useLocation().state;

	return (
		<PageLayout>
			<FailPageLayout>
				<SubTabNavbar text={location?.navTitle} closePath="/" type="close" />
				<div className="message">
					<h2 className="color">{location?.mainMessage}</h2>
					<h2>{location?.message}</h2>
				</div>
				<div className="lottie">
					<FailLottie />
				</div>
				<div className="next-btn">
					<Button handleClick={() => navigate('/')} type="Danger" text={location?.buttonText} />
				</div>
			</FailPageLayout>
		</PageLayout>
	);
}

export default FailPage;
