import React from 'react';
import Lottie, { Options } from 'react-lottie';
import PageNotFoundAnimation from './lottie.json';

function PageNotFoundLottie() {
	const defaultOptions: Options = {
		loop: true,
		autoplay: true,
		animationData: PageNotFoundAnimation,
	};

	return <Lottie options={defaultOptions} width={250} height={250} isClickToPauseDisabled />;
}

export default PageNotFoundLottie;
